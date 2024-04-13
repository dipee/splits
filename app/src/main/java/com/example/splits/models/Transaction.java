package com.example.splits.models;

import java.util.Date;

public class Transaction {
    private long id;
    private long payerId;
    private long payeeId;
    private String remark;
    private double amount;
    private Date date;

    // Getters and setters
    // ID
    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
