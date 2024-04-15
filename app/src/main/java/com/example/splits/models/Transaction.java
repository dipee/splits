package com.example.splits.models;

public class Transaction {
    private int id;
    private long payerId;
    private long payeeId;
    private String remark;
    private double amount;
    private String date;

    private int groupId;

    // Getters and setters
    // ID
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // Payer ID
    public long getPayerId() {
        return payerId;
    }
    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }
    // Payee ID
    public long getPayeeId() {
        return payeeId;
    }
    public void setPayeeId(long payeeId) {
        this.payeeId = payeeId;
    }
    // Remark
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    // Amount
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    // Date
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
