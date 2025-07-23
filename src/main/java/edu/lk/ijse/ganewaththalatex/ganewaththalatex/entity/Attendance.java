package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {
    private String attendanceId;
    private String employeeId;
    private String status;
    private LocalDate date;
}
