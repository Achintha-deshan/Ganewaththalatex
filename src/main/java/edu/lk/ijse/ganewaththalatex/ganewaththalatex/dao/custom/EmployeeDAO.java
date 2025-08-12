package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO <Employee> {
    List<String> getAllIds() throws SQLException, ClassNotFoundException;
}
