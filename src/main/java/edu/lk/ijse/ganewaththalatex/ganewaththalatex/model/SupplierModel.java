//package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;
//
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;
//import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SupplierModel {
//    public ArrayList<SupplierDto> getSuppliers() throws SQLException, ClassNotFoundException {
//
//        Connection  connection = DBConnection.getInstance().getConnection();
//
//        String sql = "select * from Supplier";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        ArrayList<SupplierDto> suppliers = new ArrayList<>();
//        ResultSet rst = preparedStatement.executeQuery();
//
//        while (rst.next()) {
//            SupplierDto supplierDto = new SupplierDto(rst.getString("Supplier_ID"), rst.getString("name") , rst.getString("contact_number"),rst.getString("bank_name"),rst.getString("account_number"), rst.getString("branch_name") );
//            suppliers.add(supplierDto);
//        }
//        return suppliers;
//
//        //ResultSet execute = CrudUtil.execute("select * from Supplier");
//    }
//
//    public boolean addSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
//        Connection  connection = DBConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO Supplier VALUES (?,?,?,?,?,?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, supplierDto.getSupplierID());
//        preparedStatement.setString(2,supplierDto.getSupplierName());
//        preparedStatement.setString(3,supplierDto.getPhoneNumber());
//        preparedStatement.setString(4,supplierDto.getBankName());
//        preparedStatement.setString(5,supplierDto.getBankAccount());
//        preparedStatement.setString(6,supplierDto.getBranchName());
//
//        return preparedStatement.executeUpdate() > 0 ? true : false;
//
//    }
//
//    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
//        Connection  connection = DBConnection.getInstance().getConnection();
//        String sql =  "SELECT Supplier_ID FROM Supplier ORDER BY Supplier.Supplier_ID DESC LIMIT 1";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet rst = preparedStatement.executeQuery();
//
//        if (rst.next()) {
//            String lastId = rst.getString("Supplier_ID").trim();
//            String lastNumberString = lastId.substring(1);
//            int lastNumber = Integer.parseInt(lastNumberString);
//            int nextId = lastNumber + 1;
//            String nextNumberString = String.format("S%03d", nextId);
//            return nextNumberString;
//        }
//        return "S001";
//    }
//
//    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute(
//                " update Supplier set name=?, contact_number=?, bank_name=?, account_number=?, branch_name=? where Supplier_ID=?",
//                supplierDto.getSupplierName(),
//                supplierDto.getPhoneNumber(),
//                supplierDto.getBankName(),
//                supplierDto.getBankAccount(),
//                supplierDto.getBranchName(),
//                supplierDto.getSupplierID()
//
//        );
//    }
//
//    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("DELETE FROM Supplier WHERE Supplier_ID = ?",id);
//    }
//    public static SupplierDto searchSupplier(String key) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM Supplier WHERE Supplier_ID = ? OR name = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, key);
//        preparedStatement.setString(2, key);
//
//        ResultSet rst = preparedStatement.executeQuery();
//        if (rst.next()) {
//            return new SupplierDto(
//                    rst.getString("Supplier_ID"),
//                    rst.getString("name"),
//                    rst.getString("contact_number"),
//                    rst.getString("bank_name"),
//                    rst.getString("account_number"),
//                    rst.getString("branch_name")
//            );
//        }
//        return null;
//    }
//}
