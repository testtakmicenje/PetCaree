package com.example.petcare.weight;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.petcare.R;

public class AddMyJobsAndMyProjects extends AppCompatActivity {

    private Toolbar mTopToolbar;

    EditText edit_text_1, edit_text_2, edit_text_3, edit_text_4, edit_text_5;

    TextView bt_save;

    public static final String DATABASE_NAME = "LinkBizMyJobsAndMyProjects.db";

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_jobs_and_my_projects);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view = getWindow().getDecorView();

        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();

        edit_text_1 = (EditText) findViewById(R.id.workernameandsurname);

        edit_text_2 = (EditText) findViewById(R.id.workeremail);

        edit_text_3 = (EditText) findViewById(R.id.workerphonenumber);

        edit_text_4 = (EditText) findViewById(R.id.workersalary);

        bt_save = (TextView) findViewById(R.id.add);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_text_1.getText().toString().trim();

                String email = edit_text_2.getText().toString().trim();

                String phone = edit_text_3.getText().toString();

                String salary = edit_text_4.getText().toString().trim();

                {

                    String insertSQL =

                            "INSERT INTO Student \n" +

                                    "(Name, Email, PhoneNo, WorkerSalary)\n" +

                                    "VALUES \n" +

                                    "(?, ?, ?, ?);";

                    mDatabase.execSQL(insertSQL, new String[]{name, email, phone, salary});

                    Intent intent = new Intent(AddMyJobsAndMyProjects.this, MyJobsAndMyProjects.class);
                    startActivity(intent);

                }

            }
        });

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
