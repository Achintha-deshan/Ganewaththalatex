package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class InventoryDto {
    private String inventoryID;
    private String supplierID;
    private String supplierName;
    private String dateAdded;
    private int quantity;
    private double rate;
    private double price;

    public InventoryDto(String inventoryID, String supplierID, String supplierName, String dateAdded, int quantity, double rate, double price) {
        this.inventoryID = inventoryID;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.rate = rate;
        this.price = price;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
