package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

import java.time.LocalDate;

public class AttendanceDto {

    private String attendanceId;
    private String employeeId;
    private String status;
    private LocalDate date;

    public AttendanceDto(String attendanceId, String employeeId, String status, LocalDate date) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.status = status;
        this.date = date;
    }

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
