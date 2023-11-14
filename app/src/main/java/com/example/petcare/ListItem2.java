package com.example.petcare;

public class ListItem2 {
    private String name;
    private int imageResourceId;
    private String description;

    public ListItem2(String name, int imageResourceId, String description) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }
}

