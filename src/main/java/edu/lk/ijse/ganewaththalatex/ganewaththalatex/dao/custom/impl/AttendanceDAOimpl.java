package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.impl;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.SQLUtil;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dao.custom.AttendanceDAO;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceDAOimpl implements AttendanceDAO {


    @Override
    public boolean save(Attendance attendance) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Attendance VALUES (?, ?, ?, ?)",
                attendance.getAttendanceId(),
                attendance.getEmployeeId(),
                attendance.getStatus(),
                attendance.getDate());
    }

    @Override
    public boolean update(Attendance attendance) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Attendance SET Employee_ID = ?, status = ?, date = ? WHERE Attendance_ID = ?",
                attendance.getEmployeeId(),
                attendance.getStatus(),
                attendance.getDate(),
                attendance.getAttendanceId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Attendance WHERE Attendance_ID = ?", id);
    }

    @Override
    public List<String> getAllids() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public List<Attendance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute(
                "SELECT a.Attendance_ID, a.Employee_ID, a.status, a.date, e.name AS employeeName " +
                        "FROM Attendance a JOIN Employee e ON a.Employee_ID = e.Employee_ID"
        );
        List<Attendance> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Attendance(
                    rs.getString("Attendance_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("status"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("employeeName")  // now you have employeeName
            ));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Optional<Attendance> findbyId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute(
                "SELECT a.Attendance_ID, a.Employee_ID, a.status, a.date, e.name AS employeeName " +
                        "FROM Attendance a JOIN Employee e ON a.Employee_ID = e.Employee_ID WHERE Attendance_ID = ?", id);
        if (rs.next()) {
            return Optional.of(new Attendance(
                    rs.getString("Attendance_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("status"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("employeeName") // fetch employeeName
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<Attendance> getAllWithEmployeeName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT a.Attendance_ID, a.Employee_ID, e.name AS employeeName, a.status, a.date " +
                "FROM Attendance a " +
                "JOIN Employee e ON a.Employee_ID = e.Employee_ID";

        ResultSet rs = SQLUtil.execute(sql);
        List<Attendance> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Attendance(
                    rs.getString("Attendance_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("status"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("employeeName")  // set employeeName here
            ));
        }
        return list;
    }

    @Override
    public int[] getAttendanceCounts(String empId, String month) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        YearMonth yearMonth = YearMonth.parse(month); // format "YYYY-MM"
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        String sql = "SELECT status FROM Attendance WHERE Employee_ID = ? AND date BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, empId);
        ps.setDate(2, Date.valueOf(startDate));
        ps.setDate(3, Date.valueOf(endDate));

        ResultSet rs = ps.executeQuery();

        int present = 0;
        int halfDay = 0;

        while (rs.next()) {
            String status = rs.getString("status");
            if ("Present".equalsIgnoreCase(status)) {
                present++;
            } else if ("Half-Day".equalsIgnoreCase(status)) {
                halfDay++;
            }
        }

        return new int[]{present, halfDay};
    }

}
