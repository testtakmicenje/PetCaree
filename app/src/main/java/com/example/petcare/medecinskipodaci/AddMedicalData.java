package com.example.petcare.medecinskipodaci;

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

public class AddMedicalData extends AppCompatActivity {

    private Toolbar mTopToolbar;

    DatePicker datePicker;
    EditText petName, petBreed, disease, medication;

    TextView add;

    public static final String DATABASE_NAME = "PetCareMedicalData.db";

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_data);
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

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createMedicalDataTable();

        datePicker = findViewById(R.id.dateDatePicker);
        petName = findViewById(R.id.animalName);
        petBreed = findViewById(R.id.animalType);
        disease = findViewById(R.id.diseaseEditText);
        medication = findViewById(R.id.medicineEditText);

        add = findViewById(R.id.submitMedicalDataButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = formatDate(year, month, dayOfMonth);

                String name = petName.getText().toString().trim();
                String breed = petBreed.getText().toString().trim();
                String petDisease = disease.getText().toString().trim();
                String petMedication = medication.getText().toString().trim();

                String insertSQL = "INSERT INTO MedicalData \n" +
                        "(Date, PetName, PetBreed, Disease, Medication)\n" +
                        "VALUES \n" +
                        "(?, ?, ?, ?, ?);";

                mDatabase.execSQL(insertSQL, new String[]{date, name, breed, petDisease, petMedication});

                Intent intent = new Intent(AddMedicalData.this, MedicalDataList.class);
                startActivity(intent);
            }
        });
    }

    private void createMedicalDataTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS MedicalData " +
                        "(\n" +
                        "    id INTEGER NOT NULL CONSTRAINT medicaldata_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    Date DATE NOT NULL,\n" +
                        "    PetName varchar(200) NOT NULL,\n" +
                        "    PetBreed varchar(200) NOT NULL,\n" +
                        "    Disease Varchar(200) NOT NULL,\n" +
                        "    Medication Varchar(200) NOT NULL\n" +
                        ");"
        );
    }

    private String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
}


