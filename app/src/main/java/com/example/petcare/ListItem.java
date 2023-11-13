package com.example.petcare;

public class ListItem {
    private String text;
    private int imageResource;

    public ListItem(String text, int imageResource) {
        this.text = text;
        this.imageResource = imageResource;
    }

    public String getText() {
        return text;
    }

    public int getImageResource() {
        return imageResource;
    }
}