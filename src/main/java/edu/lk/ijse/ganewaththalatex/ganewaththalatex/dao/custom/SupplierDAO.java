package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Supplier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SupplierDAO extends CrudDAO<Supplier> {
    List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException;
    Optional<Supplier> findById(String id) throws SQLException, ClassNotFoundException;
}
