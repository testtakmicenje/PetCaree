package com.example.petcare;
// DatabaseHelper.java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "petcare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WEIGHT_ENTRIES = "weight_entries";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PET_TYPE = "pet_type";
    private static final String COLUMN_PET_NAME = "pet_name";
    private static final String COLUMN_WEIGHT = "weight";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_WEIGHT_ENTRIES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PET_TYPE + " TEXT, " +
                COLUMN_PET_NAME + " TEXT, " +
                COLUMN_WEIGHT + " REAL);";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ovdje možete implementirati nadogradnju baze podataka
    }
    // Metoda za brisanje težine


    public void addWeight(WeightEntry weightEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATE, weightEntry.getDate());
        values.put(COLUMN_PET_TYPE, weightEntry.getPetType());
        values.put(COLUMN_PET_NAME, weightEntry.getPetName());
        values.put(COLUMN_WEIGHT, weightEntry.getWeight());

        db.insert(TABLE_WEIGHT_ENTRIES, null, values);
        db.close();
    }

    public List<WeightEntry> getAllWeights() {
        List<WeightEntry> weightList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_WEIGHT_ENTRIES;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                WeightEntry weightEntry = new WeightEntry(
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PET_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PET_NAME)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_WEIGHT))
                );
                weightList.add(weightEntry);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return weightList;
    }
}

