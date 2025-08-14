package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String orderID;
    private String factoryName;
    private String qty;
    private String orderDate;
    private String inventoryID;
    private String inventoryQTY;
    private String factoryID;

    public Order(String orderID, String orderDate, String factoryID, double fullTotal, double halfPayment) {
    }
}
