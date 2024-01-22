package com.example.petcare.fitnesiaktivnosti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.petcare.R;
import com.example.petcare.medecinskipodaci.MyMedicalInfoModel;
import com.example.petcare.prehrana.Ljubimac;
import com.example.petcare.prehrana.PermissionActivity;
import com.example.petcare.prehrana.PrehranaAdapter;

import java.util.ArrayList;
import java.util.List;

    public class FitnesIAktivnost extends AppCompatActivity {

        private Toolbar mTopToolbar;

        List<Fitnes> myJobsAndMyProjectsListModel;

        RecyclerView recyclerView;

        SQLiteDatabase mDatabase;

        FitnesIAktivnostAdapter adapter;

    public static final String DATABASE_NAME = "Fitnes.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitnes);
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

        recyclerView = (RecyclerView) findViewById(R.id.weightListView);

        recyclerView.setHasFixedSize(true);

        Button addWeightButton = findViewById(R.id.addWeightButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createEmployeeTable();

        showEmployeesFromDatabase();

        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otvorite novi ekran za unos težine
                Intent intent = new Intent(FitnesIAktivnost.this, PermissionActivityFitnes.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    private void showEmployeesFromDatabase() {

        Cursor cursorproduct = mDatabase.rawQuery("SELECT * FROM Fitnes2", null);

        List<Ljubimac> workersListModelList = new ArrayList<>();

        if (cursorproduct.moveToFirst()) {

            do {

                workersListModelList.add(new Ljubimac(

                        cursorproduct.getInt(0),

                        cursorproduct.getString(1),

                        cursorproduct.getString(2)

                ));

            }

            while (cursorproduct.moveToNext());

        }

        if (workersListModelList.isEmpty()) {

            recyclerView.setVisibility(View.INVISIBLE);

        } else {

            recyclerView.setVisibility(View.VISIBLE);

        }

        cursorproduct.close();

        adapter = new FitnesIAktivnostAdapter(this, R.layout.fitnes_list_item, workersListModelList, mDatabase);

        recyclerView.setAdapter(adapter);

        adapter.reloadEmployeesFromDatabase();

    }

    private void createEmployeeTable() {

        mDatabase.execSQL(

                "CREATE TABLE IF NOT EXISTS Fitnes2 " +

                        "(\n" +

                        "    id INTEGER NOT NULL CONSTRAINT Fitnes_pk2 PRIMARY KEY AUTOINCREMENT,\n" +

                        "    Email varchar(200) NOT NULL,\n" +

                        "    Vrijeme varchar(200) NOT NULL\n" +

                        ");"

        );
    }
}
