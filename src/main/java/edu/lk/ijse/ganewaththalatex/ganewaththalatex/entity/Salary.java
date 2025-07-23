package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Salary {
    private String salaryId;
    private String employeeId;
    private String month;
    private int totalPresentDays;
    private int totalHalfDays;
    private BigDecimal dailyRate;
    private BigDecimal totalSalary;

}
