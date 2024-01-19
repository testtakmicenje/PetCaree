package com.example.petcare.prehrana;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.petcare.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EvidencijaPrehrane extends AppCompatActivity {

    private EditText imeLjubimcaEditText;
    private TextView datumVrijemeTextView;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidencija_prehrane_activity);
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

        // Inicijalizacija elemenata
        imeLjubimcaEditText = findViewById(R.id.imeLjubimcaEditText);
        datumVrijemeTextView = findViewById(R.id.vrijemeHranjenjaText);
        calendar = Calendar.getInstance();

        // Postavljanje klika za odabir datuma i vremena
        datumVrijemeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
    }

    // Metoda za prikazivanje dijaloga za odabir datuma i vremena
    // Metoda za prikazivanje dijaloga za odabir datuma i vremena
    private void showDateTimePicker() {
        // Postavljanje trenutnog datuma i vremena u dijalog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Prikazivanje dijaloga za odabir vremena nakon odabira datuma
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                EvidencijaPrehrane.this, R.style.DatePickerTheme,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        calendar.set(Calendar.MINUTE, minute);

                                        // Formatiranje i postavljanje odabranog datuma i vremena u TextView
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                                        datumVrijemeTextView.setText(dateFormat.format(calendar.getTime()));
                                    }
                                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

                        timePickerDialog.show();

                        // Prilagodba boje dugmadi unutar DatePickerDialog-a
                        Button positiveButton = timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                        positiveButton.setTextColor(Color.parseColor("#000000")); // Boja teksta
                        positiveButton.setBackgroundColor(Color.parseColor("#FFFFFF")); // Boja pozadine

                        negativeButton.setTextColor(Color.parseColor("#000000")); // Boja teksta
                        negativeButton.setBackgroundColor(Color.parseColor("#FFFFFF")); // Boja pozadine

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

        Button positiveButton = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

        positiveButton.setTextColor(Color.parseColor("#000000")); // Boja teksta
        positiveButton.setBackgroundColor(Color.parseColor("#FFFFFF")); // Boja pozadine

        negativeButton.setTextColor(Color.parseColor("#000000")); // Boja teksta
        negativeButton.setBackgroundColor(Color.parseColor("#FFFFFF")); // Boja pozadine

    }

}
