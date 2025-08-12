package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.custom.EmployeBO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util.EmployeEntityconverter;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOFactory;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.DAOTypes;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.EmployeeDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeBOImpl implements EmployeBO {
    private final EmployeeDAO employeeDAO =
            (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    private final EmployeEntityconverter converter = new EmployeEntityconverter();

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeeList = (ArrayList<Employee>) employeeDAO.getAll();  // cast if necessary
        ArrayList<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee e : employeeList) {
            dtoList.add(converter.getEmployeeDto(e));
        }
        return dtoList;
    }

    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Employee employee = converter.getEmployee(dto);
        return employeeDAO.save(employee);
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Employee employee = converter.getEmployee(dto);
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDto findEmployeeById(String id) throws SQLException, ClassNotFoundException {
        Optional<Employee> optionalEmployee = employeeDAO.findbyId(id);
        return optionalEmployee.map(converter::getEmployeeDto).orElse(null);
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        // Assuming employeeDAO.getAllIds() returns List<String>
        List<String> idList = employeeDAO.getAllIds();
        return new ArrayList<>(idList);  // convert List to ArrayList and return
    }
}
