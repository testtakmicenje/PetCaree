package com.example.petcare.mojiljubimci;

import android.provider.BaseColumns;

public class Pet implements BaseColumns {

    // Ime tablice
    public static final String TABLE_NAME = "ljubimci";

    // Imena stupaca
    public static final String COLUMN_NAME_NAME = "ime";
    public static final String COLUMN_NAME_TYPE = "vrsta";
    public static final String COLUMN_NAME_IMAGE_PATH = "slika_path";
    public static final String COLUMN_NAME_IMAGE_URI = "slika_uri";
    public static final String COLUMN_NAME_NOTE = "napomena";

    private int id;
    private String name;
    private String type;
    private String imagePath;
    private String imageUri;
    private String note;


    // Prazan konstruktor
    public Pet() {
    }

    // Konstruktor s argumentima
    // Konstruktor s argumentima
    public Pet(String name, String type, String note) {
        this.name = name;
        this.type = type;
        this.note = note;
    }


    // Getteri i setteri
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUri() {
        return imageUri;
    }
    public String getNote() {
        return note;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
