package com.example.petcare.medecinskipodaci;
public class MyMedicalInfoModel {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phno;

    public MyMedicalInfoModel(int id, String name, String username, String email, String phno) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phno = phno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhno() {
        return phno;
    }


}
