package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDAO extends CrudDAO<Order> {
    boolean save(Order order, Connection connection) throws SQLException;

    Optional<Order> findById(String id) throws Exception;

    List<Order> findAll() throws Exception;

    String getNextOrderId() throws Exception;
}
