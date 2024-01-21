package com.example.petcare.prehrana;

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
import com.example.petcare.medecinskipodaci.MyMedicalInfoAdapter;
import com.example.petcare.medecinskipodaci.MyMedicalInfoModel;
import java.util.ArrayList;
import java.util.List;

    public class Prehrana extends AppCompatActivity {

        private Toolbar mTopToolbar;

        List<MyMedicalInfoModel> myJobsAndMyProjectsListModel;

        RecyclerView recyclerView;

        SQLiteDatabase mDatabase;

        PrehranaAdapter adapter;

    public static final String DATABASE_NAME = "Prehrana.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prehrana);
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
                Intent intent = new Intent(Prehrana.this, PermissionActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    private void showEmployeesFromDatabase() {

        Cursor cursorproduct = mDatabase.rawQuery("SELECT * FROM Prehrana2", null);

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

        adapter = new PrehranaAdapter(this, R.layout.prehana_list_item, workersListModelList, mDatabase);

        recyclerView.setAdapter(adapter);

        adapter.reloadEmployeesFromDatabase();

    }

    private void createEmployeeTable() {

        mDatabase.execSQL(

                "CREATE TABLE IF NOT EXISTS Prehrana2 " +

                        "(\n" +

                        "    id INTEGER NOT NULL CONSTRAINT Prehrana_pk2 PRIMARY KEY AUTOINCREMENT,\n" +

                        "    Email varchar(200) NOT NULL,\n" +

                        "    Vrijeme varchar(200) NOT NULL\n" +

                        ");"

        );
    }
}
