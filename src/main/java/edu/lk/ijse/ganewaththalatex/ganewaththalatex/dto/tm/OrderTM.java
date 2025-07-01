package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

public class OrderTM {
    private String orderID;
    private String factoryName;
    private String qty;
    private String orderDate;
    private String inventoryID;
    private String inventoryQTY;
    private String factoryID;
    private String halfPayment;
    private String fullTotal;
;

    public OrderTM(String orderID, String factoryName, String qty, String orderDate, String inventoryID, String inventoryQTY, String factoryID , String halfPayment, String fullTotal) {
        this.orderID = orderID;
        this.factoryName = factoryName;
        this.qty = qty;
        this.orderDate = orderDate;
        this.inventoryID = inventoryID;
        this.inventoryQTY = inventoryQTY;
        this.factoryID = factoryID;
        this.halfPayment = halfPayment;
        this.fullTotal = fullTotal;
    }

    public String getFullTotal() {
        return fullTotal;
    }

    public void setFullTotal(String fullTotal) {
        this.fullTotal = fullTotal;
    }

    public String getHalfPayment() {
        return halfPayment;
    }

    public void setHalfPayment(String halfPayment) {
        this.halfPayment = halfPayment;
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