package com.example.splits.models;

public class Participant {
    private int id;
    private int billId;
    private int userId;
    private double portionPaid;
    private double portionOwed;

    // Getters and setters
    // ID
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // Bill ID
    public long getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    // User ID
    public long getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    // Portion Paid
    public double getPortionPaid() {
        return portionPaid;
    }
    public void setPortionPaid(double portionPaid) {
        this.portionPaid = portionPaid;
    }
    // Portion Owed
    public double getPortionOwed() {
        return portionOwed;
    }
    public void setPortionOwed(double portionOwed) {
        this.portionOwed = portionOwed;
    }
}
