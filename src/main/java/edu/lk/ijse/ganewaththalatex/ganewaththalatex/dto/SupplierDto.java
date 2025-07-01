package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class SupplierDto {
    private String supplierID;
    private String supplierName;
    private String phoneNumber;
    private String bankName;
    private String bankAccount;
    private String branchName;

    public SupplierDto(String supplierID, String supplierName, String phoneNumber, String bankName, String bankAccount, String branchName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.branchName = branchName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}