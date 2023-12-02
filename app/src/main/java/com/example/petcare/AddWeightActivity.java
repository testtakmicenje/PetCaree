package com.example.petcare;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddWeightActivity extends AppCompatActivity {

    private DatePicker dateDatePicker; // Promijenjeno ime varijable
    private EditText petTypeEditText;
    private EditText petNameEditText;
    private EditText weightEditText;
    private Button addWeightButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);
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

        // Promijenjena varijabla na dateDatePicker
        dateDatePicker = findViewById(R.id.dateDatePicker);
        petTypeEditText = findViewById(R.id.petTypeEditText);
        petNameEditText = findViewById(R.id.petNameEditText);
        weightEditText = findViewById(R.id.weightEditText);
        addWeightButton = findViewById(R.id.addWeightButton);

        dbHelper = new DatabaseHelper(this);

        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dohvatite unesene podatke iz DatePicker-a
                int dayOfMonth = dateDatePicker.getDayOfMonth();
                int month = dateDatePicker.getMonth() + 1; // Mjeseci idu od 0 do 11
                int year = dateDatePicker.getYear();

                String date = year + "-" + month + "-" + dayOfMonth;

                String petType = petTypeEditText.getText().toString();
                String petName = petNameEditText.getText().toString();
                double weight = Double.parseDouble(weightEditText.getText().toString());

                // Spremite podatke u bazu podataka
                dbHelper.addWeight(new WeightEntry(date, petType, petName, weight));

                // Obavijestite korisnika da je težina unesena
                Toast.makeText(AddWeightActivity.this, "Težina je unesena", Toast.LENGTH_SHORT).show();

                // Vratite se na prethodni ekran
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
