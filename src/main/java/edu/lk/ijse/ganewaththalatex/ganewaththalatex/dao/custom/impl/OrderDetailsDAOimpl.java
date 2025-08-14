package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.OrderDetailsDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOimpl implements OrderDetailsDAO {

    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        try {
            return getNextOrderDetailsId();
        } catch (Exception e) {
            throw new SQLException("Failed to generate next OrderDetail ID", e);
        }
    }

    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO OrderDetail (OrderDetail_ID, Order_ID, Inventory_ID, QTY, QtyOnInventory) VALUES (?, ?, ?, ?, ?)",
                orderDetails.getOrderDetailsID(),
                orderDetails.getOrderID(),
                orderDetails.getInventoryID(),
                orderDetails.getQTY(),
                orderDetails.getQtyOnInventory()
        );
    }


    @Override
    public boolean save(OrderDetails entity, Connection connection) throws SQLException {
        String sql = "INSERT INTO OrderDetail (OrderDetail_ID, Order_ID, Inventory_ID, QTY, QtyOnInventory) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, entity.getOrderDetailsID());     // OD001, OD002...
            pst.setString(2, entity.getOrderID());            // FK to OrderTable
            pst.setString(3, entity.getInventoryID());        // FK to Inventory
            pst.setInt(4, Integer.parseInt(entity.getQTY())); // numeric field
            pst.setInt(5, Integer.parseInt(entity.getQtyOnInventory())); // numeric field
            return pst.executeUpdate() > 0;
        }
    }


    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE OrderDetail SET Inventory_ID=?, QTY=?, QtyOnInventory=? WHERE OrderDetail_ID=?",
                entity.getInventoryID(), entity.getQTY(), entity.getQtyOnInventory(), entity.getOrderDetailsID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM OrderDetail WHERE OrderDetail_ID=?", id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Optional<OrderDetails> findbyId(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDetails> findById(String id) throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT * FROM OrderDetail WHERE OrderDetail_ID=?", id);
        if (rs.next()) {
            return Optional.of(new OrderDetails(
                    rs.getString("OrderDetail_ID"),
                    rs.getString("Order_ID"),
                    rs.getString("Inventory_ID"),
                    rs.getString("QTY"),
                    rs.getString("QtyOnInventory")
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDetails> findAll() throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT * FROM OrderDetail");
        List<OrderDetails> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new OrderDetails(
                    rs.getString("OrderDetail_ID"),
                    rs.getString("Order_ID"),
                    rs.getString("Inventory_ID"),
                    rs.getString("QTY"),
                    rs.getString("QtyOnInventory")
            ));
        }
        return list;
    }

    @Override
    public String getNextOrderDetailsId() throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT OrderDetail_ID FROM OrderDetail ORDER BY OrderDetail_ID DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int num = Integer.parseInt(lastId.substring(2)) + 1;
            return String.format("OD%03d", num);
        }
        return "OD001";
    }
}
