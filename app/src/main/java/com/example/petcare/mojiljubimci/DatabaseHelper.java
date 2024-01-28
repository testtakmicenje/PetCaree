package com.example.petcare.mojiljubimci;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PetCareDatabase";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ljubimci";

    private static final String TABLE_PETS = "pets";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NOTE = "note";

    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_IMAGE_PATH = "image_path";

    private static final String CREATE_TABLE_PETS = "CREATE TABLE " + TABLE_PETS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_TYPE + " TEXT, "
            + COLUMN_IMAGE_PATH + " TEXT, "
            + COLUMN_NOTE + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_PETS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);
        onCreate(db);
    }

    public long addPet(String name, String type, String imagePath, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_IMAGE_PATH, imagePath);
        values.put(COLUMN_NOTE, note);
        return db.insert(TABLE_PETS, null, values);
    }

    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PETS, null);

        int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
        int typeColumnIndex = cursor.getColumnIndex(COLUMN_TYPE);
        int imagePathColumnIndex = cursor.getColumnIndex(COLUMN_IMAGE_PATH);
        int noteColumnIndex = cursor.getColumnIndex(COLUMN_NOTE);

        while (cursor.moveToNext()) {
            if (idColumnIndex != -1 && nameColumnIndex != -1 && typeColumnIndex != -1 && imagePathColumnIndex != -1) {
                Pet pet = new Pet();
                pet.setId(cursor.getInt(idColumnIndex));
                pet.setName(cursor.getString(nameColumnIndex));
                pet.setType(cursor.getString(typeColumnIndex));
                pet.setImagePath(cursor.getString(imagePathColumnIndex));


                pet.setNote(cursor.getString(noteColumnIndex));
                petList.add(pet);
            }
        }

        cursor.close();
        return petList;
    }

    public void deletePet(int petId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PETS, "id=?", new String[]{String.valueOf(petId)});


        db.close();
    }


}

