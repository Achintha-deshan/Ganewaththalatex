package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.SalaryDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaryDAOimpl  implements SalaryDAO {


    @Override
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Salary(Salary_ID, Employee_ID, month, total_present_days, total_half_days, daily_rate, total_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, salary.getSalaryId(), salary.getEmployeeId(), salary.getMonth(),
                salary.getTotalPresentDays(), salary.getTotalHalfDays(), salary.getDailyRate(), salary.getTotalSalary());
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Salary SET Employee_ID=?, month=?, total_present_days=?, total_half_days=?, daily_rate=?, total_salary=? WHERE Salary_ID=?";
        return SQLUtil.execute(sql, salary.getEmployeeId(), salary.getMonth(), salary.getTotalPresentDays(),
                salary.getTotalHalfDays(), salary.getDailyRate(), salary.getTotalSalary(), salary.getSalaryId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Salary WHERE Salary_ID=?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Salary";
        ResultSet rs = SQLUtil.execute(sql);
        List<Salary> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Salary(
                    rs.getString("Salary_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("month"),
                    rs.getInt("total_present_days"),
                    rs.getInt("total_half_days"),
                    rs.getBigDecimal("daily_rate"),
                    rs.getBigDecimal("total_salary")
            ));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Optional<Salary> findbyId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Salary WHERE Salary_ID=?";
        ResultSet rs = SQLUtil.execute(sql, id);
        if (rs.next()) {
            return Optional.of(new Salary(
                    rs.getString("Salary_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("month"),
                    rs.getInt("total_present_days"),
                    rs.getInt("total_half_days"),
                    rs.getBigDecimal("daily_rate"),
                    rs.getBigDecimal("total_salary")
            ));
        }
        return Optional.empty();
    }

    @Override
    public String getLastSalaryId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Salary_ID FROM Salary ORDER BY Salary_ID DESC LIMIT 1";
        ResultSet rs = SQLUtil.execute(sql);
        if(rs.next()) {
            return rs.getString("Salary_ID");
        }
        return null;
    }
}
