package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

import java.time.LocalDate;

public class AttendanceTM {
    private String attendanceId;
    private String employeeId;
    private String employeeName; // ✅ Corrected field name
    private String status;
    private LocalDate date;

    public AttendanceTM() {
    }

    public AttendanceTM(String attendanceId, String employeeId, String employeeName, String status, LocalDate date) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.status = status;
        this.date = date;
    }

    // ✅ Getters and setters

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
