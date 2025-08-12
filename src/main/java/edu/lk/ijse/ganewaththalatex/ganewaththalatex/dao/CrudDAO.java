package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO <T> extends SuperDAO {
    List<T> getAll() throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    boolean save(T t) throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    boolean delete (String id) throws SQLException, ClassNotFoundException;
    List<String> getAllids() throws SQLException, ClassNotFoundException;
    Optional<T> findbyId(String id) throws SQLException, ClassNotFoundException;

}
