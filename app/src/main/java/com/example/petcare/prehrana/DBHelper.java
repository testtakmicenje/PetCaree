package com.example.petcare.prehrana;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ljubimci.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Kreirajte tablicu za ljubimce
        String createTableQuery = "CREATE TABLE ljubimci ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ime TEXT, "
                + "vrijeme_obroka TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ažurirajte tablice prema potrebi
    }

    public void dodajLjubimca(Ljubimac ljubimac) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ime", ljubimac.getIme());
        values.put("vrijeme_obroka", ljubimac.getVrijemeObroka());

        // Unesi ljubimca u bazu
        db.insert("ljubimci", null, values);
        db.close();
    }
}
