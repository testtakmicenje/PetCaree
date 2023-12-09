package com.example.petcare.weight;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddMyJobsAndMyProjects extends AppCompatActivity {

    private Toolbar mTopToolbar;

    // Izmene ovde - zamenili smo EditText sa DatePicker
    DatePicker datePicker;
    EditText workeremail, workerphonenumber, workersalary;

    TextView add;

    public static final String DATABASE_NAME = "LinkBizMyJobsAndMyProjects.db";

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_jobs_and_my_projects);
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

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();

        // Izmene ovde - promenili smo ime i tip varijable, i pridružili smo je DatePicker-u
        datePicker = findViewById(R.id.dateDatePicker);
        workeremail = findViewById(R.id.workeremail);
        workerphonenumber = findViewById(R.id.workerphonenumber);
        workersalary = findViewById(R.id.workersalary);

        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Izmene ovde - dohvatanje datuma iz DatePicker-a
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = formatDate(year, month, dayOfMonth);

                String email = workeremail.getText().toString().trim();
                String phone = workerphonenumber.getText().toString();
                String salary = workersalary.getText().toString().trim();


                {

                    String insertSQL = "INSERT INTO Student \n" +
                            "(Name, Email, PhoneNo, WorkerSalary)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?);";

                    mDatabase.execSQL(insertSQL, new String[]{date, email, phone, salary});

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

    // Izmene ovde - dodata metoda za formatiranje datuma
    private String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // month is 0-based
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
}
