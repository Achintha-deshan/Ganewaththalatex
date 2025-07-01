package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.VehicleDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {

    public static ArrayList<VehicleDto> getVehicles() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Vehicle";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ArrayList<VehicleDto> vehicles = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            VehicleDto vehicleDto = new VehicleDto(
                    resultSet.getString("Vehicle_ID"),
                    resultSet.getString("Vehicle_Number")
            );
            vehicles.add(vehicleDto);
        }
        return vehicles;
    }

    public static boolean addVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Vehicle (Vehicle_ID, Vehicle_Number) VALUES (?, ?)";
        return CrudUtil.execute(sql, dto.getVehicleID(), dto.getVehicleName());
    }

    public static boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Vehicle SET Vehicle_Number = ? WHERE Vehicle_ID = ?";
        return CrudUtil.execute(sql, dto.getVehicleName(), dto.getVehicleID());
    }

    public static boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Vehicle WHERE Vehicle_ID = ?", id);
    }

    public static VehicleDto searchVehicle(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Vehicle WHERE Vehicle_ID = ?", id);
        if (rst.next()) {
            return new VehicleDto(
                    rst.getString("Vehicle_ID"),
                    rst.getString("Vehicle_Number")
            );
        }
        return null;
    }

    public static String getNextVehicleID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT Vehicle_ID FROM Vehicle ORDER BY Vehicle_ID DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rst = preparedStatement.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString("Vehicle_ID");
            if (lastId != null && lastId.length() > 1 && lastId.startsWith("V")) {
                String lastNumberString = lastId.substring(1);
                int lastNumber = Integer.parseInt(lastNumberString);
                int nextId = lastNumber + 1;
                return String.format("V%03d", nextId);
            }
        }

        return "V001";
    }

}
