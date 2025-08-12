//package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;
//
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.FactoryDto;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FactoryModel {
//
//
//    public ArrayList<FactoryDto> getFactoryList() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM Factory;";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        ArrayList<FactoryDto> factory = new ArrayList<>();
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//            FactoryDto factoryDto = new FactoryDto(resultSet.getString("Factory_ID"),resultSet.getString("name"),resultSet.getString("contact_number"),resultSet.getString("address") );
//            factory.add(factoryDto);
//        }
//        return factory;
//    }
//
//    public String getNextFactoryID() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT Factory_ID FROM Factory ORDER BY Factory.Factory_ID DESC LIMIT 1";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            String lastID = resultSet.getString(1);
//            String lastNumberString = lastID.substring(1);
//            int lastNumber = Integer.parseInt(lastNumberString);
//            int indexId = lastNumber + 1;
//            String nextNumberId = String.format("F%03d", indexId);
//            return nextNumberId;
//        }
//        return "F001";
//    }
//
//    public static boolean addFactory(FactoryDto factoryDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO Factory VALUES (?,?,?,?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,factoryDto.getFactoryID());
//        preparedStatement.setString(2,factoryDto.getFactoryName());
//        preparedStatement.setString(3,factoryDto.getPhonenumber());
//        preparedStatement.setString(4,factoryDto.getAddress());
//
//        return preparedStatement.executeUpdate() > 0 ? true : false;
//    }
//
//    public boolean updateFactory(FactoryDto factoryDto) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute(
//                "UPDATE Factory SET name=?, contact_number=?, address=? WHERE Factory_ID=?",
//                factoryDto.getFactoryName(),
//                factoryDto.getPhonenumber(),
//                factoryDto.getAddress(),
//                factoryDto.getFactoryID()
//        );
//    }
//
//    public boolean deleteFactory(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute(
//                " DELETE FROM Factory WHERE Factory_ID = ?;",id);
//    }
//
//    public static FactoryDto searchFactory(String input) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM Factory WHERE Factory_ID = ? OR name = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, input);
//        statement.setString(2, input);
//
//        ResultSet rst = statement.executeQuery();
//        if (rst.next()) {
//            return new FactoryDto(
//                    rst.getString("Factory_ID"),
//                    rst.getString("name"),
//                    rst.getString("contact_number"),
//                    rst.getString("address")
//            );
//        }
//        return null;
//    }
//}
