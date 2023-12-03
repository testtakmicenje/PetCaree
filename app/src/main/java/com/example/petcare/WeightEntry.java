package com.example.petcare;
public class WeightEntry {
    private int id;
    private String date;
    private String petType;
    private String petName;
    private double weight;
    private boolean deleted;
    private boolean markedForDeletion;

    public WeightEntry(String date, String petType, String petName, double weight) {
        this.date = date;
        this.petType = petType;
        this.petName = petName;
        this.weight = weight;
        this.deleted = false;
        this.markedForDeletion = false;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public boolean isMarkedForDeletion() {
        return markedForDeletion;
    }
    public void markForDeletion() {
        markedForDeletion = true;
    }
}
