package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    private String inventoryID;
    private String supplierID;
    private String supplierName;
    private String dateAdded;
    private int quantity;
    private double rate;
    private double price;
}
