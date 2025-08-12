package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.OrderDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOimpl implements OrderDAO {

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Order order, Connection connection) throws SQLException {
        String sql = "INSERT INTO OrderTable (...) VALUES (...)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        // set params...
        return pstm.executeUpdate() > 0;
    }


    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE OrderTable SET Factory_ID=?, FactoryName=?, OrderDate=? WHERE Order_ID=?",
                entity.getFactoryID(), entity.getFactoryName(), entity.getOrderDate(), entity.getOrderID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM OrderTable WHERE Order_ID=?", id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Optional<Order> findbyId(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findById(String id) throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT * FROM OrderTable WHERE Order_ID=?", id);
        if (rs.next()) {
            return Optional.of(new Order(
                    rs.getString("Order_ID"),
                    rs.getString("FactoryName"),
                    null,
                    rs.getString("OrderDate"),
                    null,
                    null,
                    rs.getString("Factory_ID")
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT * FROM OrderTable");
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Order(
                    rs.getString("Order_ID"),
                    rs.getString("FactoryName"),
                    null,
                    rs.getString("OrderDate"),
                    null,
                    null,
                    rs.getString("Factory_ID")
            ));
        }
        return list;
    }

    @Override
    public String getNextOrderId() throws Exception {
        ResultSet rs = SQLUtil.execute("SELECT Order_ID FROM OrderTable ORDER BY Order_ID DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int num = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("O%03d", num);
        }
        return "O001";
    }
}
