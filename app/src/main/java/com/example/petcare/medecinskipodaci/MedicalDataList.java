package com.example.petcare.medecinskipodaci;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petcare.R;
import java.util.ArrayList;
import java.util.List;

public class MedicalDataList extends AppCompatActivity {

    private Toolbar mTopToolbar;

    List<MedicalDataModel> medicalDataModelList;

    RecyclerView recyclerView;

    SQLiteDatabase mDatabase;

    MedicalDataAdapter adapter;

    public static final String DATABASE_NAME = "PetCareMedicalData.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_data_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView backImageView = findViewById(R.id.logoImageView1);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.medicalDataListView);
        recyclerView.setHasFixedSize(true);

        Button addWeightButton = findViewById(R.id.addMedicalDataButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createMedicalDataTable();

        showMedicalDataFromDatabase();

        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalDataList.this, AddMedicalData.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void showMedicalDataFromDatabase() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM MedicalData", null);
        List<MedicalDataModel> medicalDataModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                medicalDataModelList.add(new MedicalDataModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }

        if (medicalDataModelList.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }

        cursor.close();

        adapter = new MedicalDataAdapter(this, R.layout.cardview_medical_data, medicalDataModelList, mDatabase);
        recyclerView.setAdapter(adapter);
        adapter.reloadMedicalDataFromDatabase();
    }

    private void createMedicalDataTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS MedicalData " +
                        "(\n" +
                        "    id INTEGER NOT NULL CONSTRAINT medicaldata_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    Date DATE NOT NULL,\n" +
                        "    PetName varchar(200) NOT NULL,\n" +
                        "    PetBreed varchar(200) NOT NULL\n" +
                        ");"
        );
    }
}
