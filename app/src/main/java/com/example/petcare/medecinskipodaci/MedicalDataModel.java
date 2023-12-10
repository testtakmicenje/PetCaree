package com.example.petcare.medecinskipodaci;

public class MedicalDataModel {
    private int id;
    private String date;
    private String petName;
    private String petBreed;

    public MedicalDataModel(int id, String date, String petName, String petBreed) {
        this.id = id;
        this.date = date;
        this.petName = petName;
        this.petBreed = petBreed;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetBreed() {
        return petBreed;
    }
}
