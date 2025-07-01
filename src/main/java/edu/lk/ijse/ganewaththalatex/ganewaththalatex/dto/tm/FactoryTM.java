package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

public class FactoryTM {
    private String factoryID;
    private String factoryName;
    private String phonenumber;
    private String address;

    public FactoryTM(String factoryID, String factoryName, String phonenumber, String address) {
        this.factoryID = factoryID;
        this.factoryName = factoryName;
        this.phonenumber = phonenumber;
        this.address = address;
    }

    public String getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
