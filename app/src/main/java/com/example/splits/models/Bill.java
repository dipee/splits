package com.example.splits.models;

import java.util.Date;

public class Bill {
    private int id;

    private String title;
    private String description;
    private int  payerUserId;

    private int groupId;
    private int amount;

    private String date;

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPayerUserId() {
        return payerUserId;
    }

    public void setPayerUserId(int payerUserId) {
        this.payerUserId = payerUserId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
