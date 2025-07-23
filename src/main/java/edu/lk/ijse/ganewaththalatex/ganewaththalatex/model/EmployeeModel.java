package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.EmployeeDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModel {
    public static ArrayList<EmployeeDto> getEmployees() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "select * from Employee";
        PreparedStatement ps = connection.prepareStatement(sql);

        ArrayList<EmployeeDto> employees = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
       EmployeeDto employeeDto = new EmployeeDto(resultSet.getNString("Employee_ID"), resultSet.getNString("name"), resultSet.getNString("contact_number"));
       employees.add(employeeDto);
        }
        return employees;
    }

    public static String getEmployeeID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT Employee_ID FROM Employee ORDER BY Employee.Employee_ID DESC LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            String lastID = resultSet.getNString("Employee_ID").trim();
            String LastNumberString = lastID.substring(1);
            int LastNumber = Integer.parseInt(LastNumberString);
            int nextID = LastNumber + 1;
            String nextNumberString = String.format("E%03d", nextID);
            return nextNumberString;
        }
        return "E001";
    }

    public static boolean addEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "insert into Employee values(?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,employeeDto.getEmployerID());
        ps.setString(2,employeeDto.getEmployerName());
        ps.setString(3,employeeDto.getPhonenumber());

        return ps.executeUpdate() > 0 ? true : false;

    }

    public static boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                  "update Employee set name=?, contact_number=? where Employee_ID=?",
                employeeDto.getEmployerName(),
                employeeDto.getPhonenumber(),
                employeeDto.getEmployerID()
        );
    }

    public static boolean deleteEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Employee WHERE Employee_ID = ?", employeeID);
    }

    public static EmployeeDto searchEmployee(String input) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Employee WHERE Employee_ID = ? OR name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, input);
        ps.setString(2, input);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new EmployeeDto(
                    rs.getString("Employee_ID"),
                    rs.getString("name"),
                    rs.getString("contact_number")
            );
        }
        return null;
    }

    public static List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        // Get all employee IDs from the database
        ArrayList<EmployeeDto> employees = getEmployees();
        // Return only the list of IDs as strings
        return employees.stream()
                .map(EmployeeDto::getEmployerID)
                .collect(Collectors.toList());
    }

    public static String getNameById(String id) throws SQLException, ClassNotFoundException {
        EmployeeDto emp = searchEmployee(id);
        if (emp != null) {
            return emp.getEmployerName();
        }
        return "";
    }


}
