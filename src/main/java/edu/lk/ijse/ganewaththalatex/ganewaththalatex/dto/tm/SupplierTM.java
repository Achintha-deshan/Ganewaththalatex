package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

public class SupplierTM {
    private String supplierId;
    private String supplierName;
    private String phoneNumber;
    private String bankName;
    private String accountNumber;
    private String branchName;

    public SupplierTM(String supplierId, String supplierName, String phoneNumber, String bankName, String accountNumber, String branchName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.branchName = branchName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}