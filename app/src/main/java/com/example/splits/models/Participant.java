package com.example.splits.models;

public class Participant {
    private long id;
    private long billId;
    private long userId;
    private double portionPaid;
    private double portionOwed;

    // Getters and setters
    // ID
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    // Bill ID
    public long getBillId() {
        return billId;
    }
    public void setBillId(long billId) {
        this.billId = billId;
    }
    // User ID
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
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
