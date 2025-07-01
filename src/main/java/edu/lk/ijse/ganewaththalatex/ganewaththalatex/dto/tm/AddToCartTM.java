package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm;

import javafx.scene.control.Button;

public class AddToCartTM {
    private String orderID;
    private String inventoryID;
    private double qty;
    private double price;
    private Button action;

    public AddToCartTM() {
    }

    public AddToCartTM(String orderID, String inventoryID, double qty, double price, Button action) {
        this.orderID = orderID;
        this.inventoryID = inventoryID;
        this.qty = qty;
        this.price = price;
        this.action = action;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
}