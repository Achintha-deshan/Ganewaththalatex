package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor
@Data
public class Payment {
    private String paymentType;
    private double amount;
}
