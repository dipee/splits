package com.example.splits.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Group {
    private int id;
    private String name;
    private String description;

    private String creatorId;

    private String creationDate;

    public Group() {
    }
    public Group(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        //convert string to date
        this.creationDate = creationDate;


    }
}
