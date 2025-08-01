package edu.lk.ijse.ganewaththalatex.ganewaththalatex.model;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.db.DBConnection;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.AttendanceDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm.AttendanceTM;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {
    public static boolean saveAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Attendance VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dto.getAttendanceId());
        ps.setString(2, dto.getEmployeeId());
        ps.setString(3, dto.getStatus());
        ps.setDate(4, Date.valueOf(dto.getDate()));

        return ps.executeUpdate() > 0;
    }

    public static List<AttendanceTM> getAllAttendance() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT a.Attendance_ID, a.Employee_ID, e.name AS employeeName, a.status, a.date " +
                "FROM Attendance a " +
                "JOIN Employee e ON a.Employee_ID = e.Employee_ID";

        ResultSet rs = con.createStatement().executeQuery(sql);
        List<AttendanceTM> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new AttendanceTM(
                    rs.getString("Attendance_ID"),
                    rs.getString("Employee_ID"),
                    rs.getString("employeeName"),
                    rs.getString("status"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return list;
    }

    public static boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Attendance SET Employee_ID = ?, status = ?, date = ? WHERE Attendance_ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dto.getEmployeeId());
        ps.setString(2, dto.getStatus());
        ps.setDate(3, Date.valueOf(dto.getDate()));
        ps.setString(4, dto.getAttendanceId());

        return ps.executeUpdate() > 0;
    }

    public static boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Attendance WHERE Attendance_ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, attendanceId);

        return ps.executeUpdate() > 0;
    }



    public static int[] getAttendanceCounts(String empId, String month) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        // Convert "YYYY-MM" to start and end dates
        YearMonth yearMonth = YearMonth.parse(month); // Example: "2025-07"
        LocalDate startDate = yearMonth.atDay(1);     // e.g., 2025-07-01
        LocalDate endDate = yearMonth.atEndOfMonth(); // e.g., 2025-07-31

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
