package com.example.petcare.medecinskipodaci;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;
import com.example.petcare.weight.MyJobsAndMyProjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddMedicalInfo extends AppCompatActivity {

    private Toolbar mTopToolbar;


    DatePicker datePicker;
    EditText workeremail, workerphonenumber, workersalary, workersalary2;

    TextView add;

    public static final String DATABASE_NAME = "MedicalInfo.db";

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_info);
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
        workersalary2 = findViewById(R.id.workersalary2);

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
                String salary2 = workersalary2.getText().toString().trim();

                if (date.isEmpty() || email.isEmpty() || phone.isEmpty() || salary.isEmpty() || salary2.isEmpty()) {
                    // Ako neki od podataka nedostaje, prikaži Toast
                    showToast("Molimo vas da unesete sve podatke.");
                } else {
                    // Svi potrebni podaci su uneseni, možete izvršiti unos u bazu podataka
                    String insertSQL = "INSERT INTO Student2 \n" +
                            "(Date, Email, PhoneNo, WorkerSalary, Lijek)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?, ?);";

                    mDatabase.execSQL(insertSQL, new String[]{date, email, phone, salary, salary2});

                    Intent intent = new Intent(AddMedicalInfo.this, MyMedicalInfo.class);
                    startActivity(intent);
                }
            }
        });
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

    // Izmene ovde - dodata metoda za formatiranje datuma
    private String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // month is 0-based
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
    private void showToast(String message) {
        Toast.makeText(AddMedicalInfo.this, message, Toast.LENGTH_SHORT).show();
    }
}
