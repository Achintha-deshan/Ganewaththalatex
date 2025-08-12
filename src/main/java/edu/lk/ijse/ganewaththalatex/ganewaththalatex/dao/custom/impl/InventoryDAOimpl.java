package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.InventoryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Inventory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryDAOimpl implements InventoryDAO {

    @Override
    public List<Inventory> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Inventory entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Inventory (Inventory_ID, Supplier_ID, dateAdded, quantity, Price) VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,
                entity.getInventoryID(),
                entity.getSupplierID(),
                entity.getDateAdded(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }

    @Override
    public boolean update(Inventory entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Inventory SET Supplier_ID = ?, dateAdded = ?, quantity = ?, Price = ? WHERE Inventory_ID = ?";
        return CrudUtil.execute(sql,
                entity.getSupplierID(),
                entity.getDateAdded(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getInventoryID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Inventory WHERE Inventory_ID = ?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Optional<Inventory> findbyId(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Inventory> findById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT i.Inventory_ID, i.Supplier_ID, s.name, i.DateAdded, i.quantity, i.price " +
                "FROM Inventory i JOIN Supplier s ON i.Supplier_ID = s.Supplier_ID WHERE i.Inventory_ID = ?";
        ResultSet rs = CrudUtil.execute(sql, id);

        if (rs.next()) {
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double rate = (quantity != 0) ? price / quantity : 0;

            return Optional.of(new Inventory(
                    rs.getString("Inventory_ID"),
                    rs.getString("Supplier_ID"),
                    rs.getString("name"),
                    rs.getString("DateAdded"),
                    quantity,
                    rate,
                    price
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<Inventory> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT i.Inventory_ID, i.Supplier_ID, s.name, i.DateAdded, i.quantity, i.price " +
                "FROM Inventory i JOIN Supplier s ON i.Supplier_ID = s.Supplier_ID";
        ResultSet rs = CrudUtil.execute(sql);

        List<Inventory> list = new ArrayList<>();
        while (rs.next()) {
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double rate = (quantity != 0) ? price / quantity : 0;

            list.add(new Inventory(
                    rs.getString("Inventory_ID"),
                    rs.getString("Supplier_ID"),
                    rs.getString("name"),
                    rs.getString("DateAdded"),
                    quantity,
                    rate,
                    price
            ));
        }
        return list;
    }

    @Override
    public boolean updateQuantity(String inventoryId, int changeQty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int getTotalQuantityForInventory(String inventoryID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(quantity) AS totalQty FROM Inventory WHERE Inventory_ID = ?";
        ResultSet rs = CrudUtil.execute(sql, inventoryID);
        if (rs.next()) {
            return rs.getInt("totalQty");
        }
        return 0;
    }

    @Override
    public boolean updateQuantity(String inventoryID, int qtyChange, Connection connection) throws SQLException {
        String sql = "UPDATE Inventory SET quantity = quantity + ? WHERE Inventory_ID = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, qtyChange);
            pst.setString(2, inventoryID);
            return pst.executeUpdate() > 0;
        }
    }


}
