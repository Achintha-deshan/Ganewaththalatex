package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.EmployeeDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Employee(
                    rs.getString("Employee_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number")
            ));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Employee_ID FROM Employee ORDER BY Employee_ID DESC LIMIT 1");
        if (rs.next()) {
            String lastID = rs.getString(1).trim();
            int num = Integer.parseInt(lastID.substring(1)) + 1;
            return String.format("E%03d", num);
        }
        return "E001";
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO Employee VALUES(?,?,?)",
                employee.getEmployerID(),
                employee.getEmployerName(),
                employee.getPhoneNumber()
        );
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Employee SET name=?, contact_number=? WHERE Employee_ID=?",
                employee.getEmployerName(),
                employee.getPhoneNumber(),
                employee.getEmployerID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE Employee_ID=?", id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Employee_ID FROM Employee");
        List<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }

    @Override
    public Optional<Employee> findbyId(String id) throws SQLException {
        try {
            ResultSet rs = SQLUtil.execute("SELECT * FROM Employee WHERE Employee_ID=?", id);
            if (rs.next()) {
                return Optional.of(new Employee(
                        rs.getString("Employee_ID"),
                        rs.getString("name"),
                        rs.getString("contact_number")
                ));
            }
            return Optional.empty();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT Employee_ID FROM Employee");
        List<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("Employee_ID"));
        }
        return ids;
    }
}
