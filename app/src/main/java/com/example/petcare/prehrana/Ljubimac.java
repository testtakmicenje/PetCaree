package com.example.petcare.prehrana;

public class Ljubimac {
    private int id;
    private String email;

    private String vrijeme;

    public Ljubimac(int id, String email, String vrijeme) {
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