package com.example.petcare.weight;

public class MyJobsAndMyProjectsModel {
    private int id;
    private String data;
    private String email;
    private String phone;
    private String sallary;

    public MyJobsAndMyProjectsModel(int id, String data, String email, String phone, String sallary) {
        this.id = id;
        this.data = data;
        this.email = email;
        this.phone = phone;
        this.sallary = sallary;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSallary() {
        return sallary;
    }


}
