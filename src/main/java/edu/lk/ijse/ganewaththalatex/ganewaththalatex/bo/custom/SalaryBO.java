package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {

    boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;
    boolean updateSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;
    boolean deleteSalary(String salaryId) throws SQLException, ClassNotFoundException;
    List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;
    SalaryDTO findSalaryById(String salaryId) throws SQLException, ClassNotFoundException;
//    List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;
    String getNextSalaryId() throws SQLException, ClassNotFoundException;
}
