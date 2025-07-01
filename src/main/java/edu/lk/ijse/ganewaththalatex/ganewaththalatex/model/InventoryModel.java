package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.InventoryDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    public static List<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT i.Inventory_ID, i.Supplier_ID, s.name, i.DateAdded, i.quantity, i.price " +
                "FROM Inventory i JOIN Supplier s ON i.Supplier_ID = s.Supplier_ID";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<InventoryDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new InventoryDto(
                    rs.getString("Inventory_ID"),
                    rs.getString("Supplier_ID"),
                    rs.getString("name"),
                    rs.getString("DateAdded"),
                    rs.getInt("quantity"),
                    0.0,
                    rs.getDouble("price")
            ));
        }


        return list;
    }

    public static String getInventoryID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT Inventory_ID FROM inventory ORDER BY inventory.Inventory_ID DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String lastID = resultSet.getString("Inventory_ID").trim();
            String lastNummberString = lastID.substring(1);
            int lastNummber = Integer.parseInt(lastNummberString);
            int nextID = lastNummber + 1;
            String nextNummberString = String.format("I%03d", nextID);
            return nextNummberString;
        }
        return "I001";
    }
    public static boolean addInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        try {
            con.setAutoCommit(false);

            String sql = "INSERT INTO Inventory (Inventory_ID, Supplier_ID, dateAdded, quantity,Price) VALUES (?,?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dto.getInventoryID());
            ps.setString(2, dto.getSupplierID());
            ps.setString(3, dto.getDateAdded());
            ps.setInt(4, dto.getQuantity());
            ps.setDouble(5, dto.getPrice());

            boolean isSaved = ps.executeUpdate() > 0;

            if (isSaved) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static int getTotalQuantityforInventory(String inventoryID) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT SUM(quantity) AS totalQty FROM Inventory WHERE Inventory_ID = ?";


        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, inventoryID);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("totalQty");
        }
        return 0;
    }

    public static boolean deleteInventory(String inventoryID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Inventory WHERE Inventory_ID = ?", inventoryID);
    }

    public  boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Inventory SET Supplier_ID = ?, dateAdded = ?, quantity = ? WHERE Inventory_ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, dto.getSupplierID());
        ps.setString(2, dto.getDateAdded());
        ps.setInt(3, dto.getQuantity());
        ps.setString(4, dto.getInventoryID());

        return ps.executeUpdate() > 0;
    }

    public static InventoryDto searchInventory(String inventoryID) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT i.Inventory_ID, i.Supplier_ID, s.name, i.DateAdded, i.quantity, i.price " +
                "FROM Inventory i JOIN Supplier s ON i.Supplier_ID = s.Supplier_ID " +
                "WHERE i.Inventory_ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, inventoryID);
        ResultSet rst = ps.executeQuery();

        if (rst.next()) {
            String inventoryId = rst.getString("Inventory_ID");
            String supplierId = rst.getString("Supplier_ID");
            String supplierName = rst.getString("name");
            String dateAdded = rst.getString("DateAdded");
            int quantity = rst.getInt("quantity");
            double price = rst.getDouble("price");
            double rate = quantity != 0 ? price / quantity : 0;

            return new InventoryDto(
                    inventoryId,
                    supplierId,
                    supplierName,
                    dateAdded,
                    quantity,
                    rate,
                    price
            );
        }
        return null;
    }

}
