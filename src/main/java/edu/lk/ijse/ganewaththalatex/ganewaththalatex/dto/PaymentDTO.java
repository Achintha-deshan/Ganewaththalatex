package edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto;

public class PaymentDTO {

    private String paymentType;
    private double amount;

    public PaymentDTO(String paymentType, double amount) {
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}