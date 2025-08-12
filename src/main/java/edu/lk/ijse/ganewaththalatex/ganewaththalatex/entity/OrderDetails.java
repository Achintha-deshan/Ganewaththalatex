package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetails {
    private String orderDetailsID;
    private String orderID;
    private String InventoryID;
    private String QTY;
    private String QtyOnInventory;
}
