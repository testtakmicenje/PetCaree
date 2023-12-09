package com.example.petcare.weight;

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

public class MyJobsAndMyProjects extends AppCompatActivity {

    private Toolbar mTopToolbar;

    List<MyJobsAndMyProjectsModel> myJobsAndMyProjectsListModel;

    RecyclerView recyclerView;

    SQLiteDatabase mDatabase;

    MyJobsAndMyProjectsAdapter adapter;

    public static final String DATABASE_NAME = "LinkBizMyJobsAndMyProjects.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new3_activity);
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
                Intent intent = new Intent(MyJobsAndMyProjects.this, AddMyJobsAndMyProjects.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    private void showEmployeesFromDatabase() {

        Cursor cursorproduct = mDatabase.rawQuery("SELECT * FROM Student", null);

        List<MyJobsAndMyProjectsModel> workersListModelList = new ArrayList<>();

        if (cursorproduct.moveToFirst()) {

            do {

                workersListModelList.add(new MyJobsAndMyProjectsModel(

                        cursorproduct.getInt(0),

                        cursorproduct.getString(1),

                        cursorproduct.getString(2),

                        cursorproduct.getString(3),

                        cursorproduct.getString(4)

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

        adapter = new MyJobsAndMyProjectsAdapter(this, R.layout.workers_list_item, workersListModelList, mDatabase);

        recyclerView.setAdapter(adapter);

        adapter.reloadEmployeesFromDatabase();

    }

    private void createEmployeeTable() {

        mDatabase.execSQL(

                "CREATE TABLE IF NOT EXISTS Student " +

                        "(\n" +

                        "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +

                        "    Name varchar(200) NOT NULL,\n" +

                        "    Email varchar(200) NOT NULL,\n" +

                        "    PhoneNo Varchar(200) NOT NULL, \n" +

                        "    WorkerSalary Varchar(200) NOT NULL \n" +

                        ");"

        );
    }
}
