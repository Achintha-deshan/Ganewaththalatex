package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.SuperBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeBO extends SuperBO {
    ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;
    String getNextEmployeeId() throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;



    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    EmployeeDto findEmployeeById(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
}
