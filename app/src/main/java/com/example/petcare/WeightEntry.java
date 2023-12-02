package com.example.petcare;

public class WeightEntry {
    private int id;
    private String date;
    private String petType;
    private String petName;
    private double weight;

    public WeightEntry(String date, String petType, String petName, double weight) {
        this.date = date;
        this.petType = petType;
        this.petName = petName;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getPetType() {
        return petType;
    }

    public String getPetName() {
        return petName;
    }

    public double getWeight() {
        return weight;
    }
}

