package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SalaryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public static boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Salary(Salary_ID, Employee_ID, month, total_present_days, total_half_days, daily_rate, total_salary, bank_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, salary.getSalaryId());
        pstm.setString(2, salary.getEmployeeId());
        pstm.setString(3, salary.getMonth());
        pstm.setInt(4, salary.getTotalPresentDays());
        pstm.setInt(5, salary.getTotalHalfDays());
        pstm.setBigDecimal(6, salary.getDailyRate());      // BigDecimal here
        pstm.setBigDecimal(7, salary.getTotalSalary());    // BigDecimal here
        pstm.setString(8, salary.getBankDetails());

        return pstm.executeUpdate() > 0;
    }

    // Update existing salary record
    public static boolean updateSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Salary SET Employee_ID=?, month=?, total_present_days=?, total_half_days=?, daily_rate=?, total_salary=?, bank_details=? WHERE Salary_ID=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, salary.getEmployeeId());
        pstm.setString(2, salary.getMonth());
        pstm.setInt(3, salary.getTotalPresentDays());
        pstm.setInt(4, salary.getTotalHalfDays());
        pstm.setBigDecimal(6, salary.getDailyRate());      // BigDecimal here
        pstm.setBigDecimal(7, salary.getTotalSalary());
        pstm.setString(7, salary.getBankDetails());
        pstm.setString(8, salary.getSalaryId());
        return pstm.executeUpdate() > 0;
    }

    // Delete salary by Salary_ID
    public static boolean deleteSalary(String salaryId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Salary WHERE Salary_ID=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, salaryId);
        return pstm.executeUpdate() > 0;
    }

    // Get all salaries
    public static List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Salary";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<SalaryDTO> list = new ArrayList<>();
        while (rs.next()) {
            SalaryDTO salary = new SalaryDTO(
                    rs.getString("Salary_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("month"),
                    rs.getInt("total_present_days"),
                    rs.getInt("total_half_days"),
                    rs.getBigDecimal("daily_rate"),
                    rs.getBigDecimal("total_salary")
            );
            // If you need bankDetails, you can add a setter or extend constructor
            list.add(salary);
        }
        return list;
    }
}
