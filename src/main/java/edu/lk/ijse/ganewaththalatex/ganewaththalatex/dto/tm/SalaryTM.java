package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

public class SalaryTM {
    private String salaryId;
    private String employeeId;
    private String employeeName;
    private String month;
    private int totalPresentDays;
    private int totalHalfDays;
    private double dailyRate;
    private double totalSalary;

    public SalaryTM(String salaryId, String employeeId, String employeeName, String month, int totalPresentDays, int totalHalfDays, double dailyRate, double totalSalary) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
