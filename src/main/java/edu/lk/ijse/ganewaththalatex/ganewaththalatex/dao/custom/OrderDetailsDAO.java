package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    boolean save(OrderDetails entity, Connection connection) throws SQLException;

    Optional<OrderDetails> findById(String id) throws Exception;

    List<OrderDetails> findAll() throws Exception;

    String getNextOrderDetailsId() throws Exception;
}
