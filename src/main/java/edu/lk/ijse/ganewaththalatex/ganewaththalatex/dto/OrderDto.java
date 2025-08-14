package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class OrderDto {
    private String orderID;
    private String factoryName;
    private String qty;
    private String orderDate;
    private String inventoryID;
    private String inventoryQTY;
    private String factoryID;
    private double fullTotal;   // <-- add this
    private double halfPayment;

    public OrderDto(double fullTotal, double halfPayment) {
        this.fullTotal = fullTotal;
        this.halfPayment = halfPayment;
    }

    ;

    public double getFullTotal() {
        return fullTotal;
    }

    public void setFullTotal(double fullTotal) {
        this.fullTotal = fullTotal;
    }

    public double getHalfPayment() {
        return halfPayment;
    }

    public void setHalfPayment(double halfPayment) {
        this.halfPayment = halfPayment;
    }

    public OrderDto(String orderID, String factoryName, String qty, String orderDate, String inventoryID, String inventoryQTY, String factoryID) {
        this.orderID = orderID;
        this.factoryName = factoryName;
        this.qty = qty;
        this.orderDate = orderDate;
        this.inventoryID = inventoryID;
        this.inventoryQTY = inventoryQTY;
        this.factoryID = factoryID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getInventoryQTY() {
        return inventoryQTY;
    }

    public void setInventoryQTY(String inventoryQTY) {
        this.inventoryQTY = inventoryQTY;
    }

    public String getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }



}