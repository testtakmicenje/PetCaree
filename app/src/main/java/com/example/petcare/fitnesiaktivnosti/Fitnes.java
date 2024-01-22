package com.example.petcare.fitnesiaktivnosti;

public class Fitnes {
    private int id;
    private String email;

    private String vrijeme;

    public Fitnes(int id, String email, String vrijeme) {
        this.id = id;
        this.vrijeme = vrijeme;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getVrijeme() {
        return vrijeme;
    }

}