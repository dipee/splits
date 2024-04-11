package com.example.splits.models;

import java.util.Date;

public class Entry {
    private int id;
    private String description;
    private int  byUserId;
    private int amount;

    private Date date;

    public Entry(int id, String description, int byUserId, int amount, Date date) {
        this.id = id;
        this.description = description;
        this.byUserId = byUserId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getByUserId() {
        return byUserId;
    }

    public void setByUserId(int byUserId) {
        this.byUserId = byUserId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
