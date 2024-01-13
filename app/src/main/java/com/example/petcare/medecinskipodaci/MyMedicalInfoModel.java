package com.example.petcare.medecinskipodaci;
public class MyMedicalInfoModel {
    private int id;
    private String date;
    private String username;
    private String email;
    private String phno;

    private String lijek;

    public MyMedicalInfoModel(int id, String date, String username, String email, String phno, String lijek) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.email = email;
        this.phno = phno;
        this.lijek = lijek;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
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

    public String getLijek() {
        return lijek;
    }

}
