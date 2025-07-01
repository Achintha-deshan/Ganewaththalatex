package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class EmployeeDto {
    private String employerID;
    private String employerName;
    private String phonenumber;

    public EmployeeDto(String employerID, String employerName, String phonenumber) {
        this.employerID = employerID;
        this.employerName = employerName;
        this.phonenumber = phonenumber;
    }

    public String getEmployerID() {
        return employerID;
    }

    public void setEmployerID(String employerID) {
        this.employerID = employerID;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
