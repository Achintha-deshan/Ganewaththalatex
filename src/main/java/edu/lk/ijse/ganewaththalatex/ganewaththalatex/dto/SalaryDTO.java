package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

import java.math.BigDecimal;

public class SalaryDTO {
    private String salaryId;
    private String employeeId;
    private String month;
    private int totalPresentDays;
    private int totalHalfDays;
    private BigDecimal dailyRate;
    private BigDecimal totalSalary;

    // Constructor without bankDetails (since it's not used)
    public SalaryDTO(String salaryId, String employeeId, String month, int totalPresentDays, int totalHalfDays, BigDecimal dailyRate, BigDecimal totalSalary) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.month = month;
        this.totalPresentDays = totalPresentDays;
        this.totalHalfDays = totalHalfDays;
        this.dailyRate = dailyRate;
        this.totalSalary = totalSalary;
    }

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalPresentDays() {
        return totalPresentDays;
    }

    public void setTotalPresentDays(int totalPresentDays) {
        this.totalPresentDays = totalPresentDays;
    }

    public int getTotalHalfDays() {
        return totalHalfDays;
    }

    public void setTotalHalfDays(int totalHalfDays) {
        this.totalHalfDays = totalHalfDays;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }


}
