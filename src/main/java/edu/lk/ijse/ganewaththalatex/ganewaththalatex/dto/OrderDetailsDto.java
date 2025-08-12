package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class OrderDetailsDto {
    private String orderDetailsID;
    private String orderID;
    private String inventoryID;
    private int qty;
    private int qtyOnInventory;

    public OrderDetailsDto(String orderDetailsID, String orderID, String inventoryID, int qty, int qtyOnInventory) {
        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.inventoryID = inventoryID;
        this.qty = qty;
        this.qtyOnInventory = qtyOnInventory;
    }

    public String getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(String orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQtyOnInventory() {
        return qtyOnInventory;
    }

    public void setQtyOnInventory(int qtyOnInventory) {
        this.qtyOnInventory = qtyOnInventory;
    }
}
