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
import com.example.petcare.weight.AddMyJobsAndMyProjects;
import com.example.petcare.weight.MyJobsAndMyProjectsAdapter;
import com.example.petcare.weight.MyJobsAndMyProjectsModel;

import java.util.ArrayList;
import java.util.List;

public class MyMedicalInfo extends AppCompatActivity {

    private Toolbar mTopToolbar;

    List<MyMedicalInfoModel> myJobsAndMyProjectsListModel;

    RecyclerView recyclerView;

    SQLiteDatabase mDatabase;

    MyMedicalInfoAdapter adapter;

    public static final String DATABASE_NAME = "MedicalInfo.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymedicalinfo_activity);
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

                Intent intent = new Intent(MyMedicalInfo.this, AddMedicalInfo.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    private void showEmployeesFromDatabase() {

        Cursor cursorproduct = mDatabase.rawQuery("SELECT * FROM Student2", null);

        List<MyMedicalInfoModel> workersListModelList = new ArrayList<>();

        if (cursorproduct.moveToFirst()) {

            do {

                workersListModelList.add(new MyMedicalInfoModel(

                        cursorproduct.getInt(0),

                        cursorproduct.getString(1),

                        cursorproduct.getString(2),

                        cursorproduct.getString(3),

                        cursorproduct.getString(4),

                        cursorproduct.getString(5)

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

        adapter = new MyMedicalInfoAdapter(this, R.layout.medical_info_list_item, workersListModelList, mDatabase);

        recyclerView.setAdapter(adapter);

        adapter.reloadEmployeesFromDatabase();

    }

    private void createEmployeeTable() {

        mDatabase.execSQL(

                "CREATE TABLE IF NOT EXISTS Student2 " +

                        "(\n" +

                        "    id INTEGER NOT NULL CONSTRAINT employees_pk2 PRIMARY KEY AUTOINCREMENT,\n" +

                        "    Date varchar(200) NOT NULL,\n" +

                        "    Email varchar(200) NOT NULL,\n" +

                        "    PhoneNo Varchar(200) NOT NULL, \n" +

                        "    WorkerSalary Varchar(200) NOT NULL, \n" +

                        "    Lijek Varchar(200) NOT NULL \n" +

                        ");"

        );
    }
}
