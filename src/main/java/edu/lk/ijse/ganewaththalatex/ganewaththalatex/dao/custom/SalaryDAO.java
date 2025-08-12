package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.CrudDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Salary;

import java.sql.SQLException;
import java.util.List;

public interface SalaryDAO extends CrudDAO<Salary> {
    String getLastSalaryId() throws SQLException, ClassNotFoundException;
//    List<Salary> getAll() throws SQLException, ClassNotFoundException;
}
