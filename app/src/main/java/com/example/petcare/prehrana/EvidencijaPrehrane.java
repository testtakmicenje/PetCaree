package com.example.petcare.prehrana;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;
import com.example.petcare.prehrana.ReminderBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EvidencijaPrehrane extends AppCompatActivity {

    private EditText imeLjubimcaEditText;
    private TextView vrijemeHranjenjaText;
    private TextView datumHranjenjaText;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar selectedDateTime;
    private DBHelper dbHelper;
    private Switch obavjestenjeSwitch;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NOTIFICATIONS_ENABLED_KEY = "notifications_enabled";


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

        imeLjubimcaEditText = findViewById(R.id.imeLjubimcaEditText);
        vrijemeHranjenjaText = findViewById(R.id.vrijemeHranjenjaText);
        datumHranjenjaText = findViewById(R.id.datumHranjenjaText);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);

        dbHelper = new DBHelper(this);

        // Inicijalizacija odabira za TimePicker i DatePicker
        setupDateTimePickers();

        // Postavljanje klika na tekst za unos vremena
        vrijemeHranjenjaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrijemeHranjenjaText.setActivated(true);
                datumHranjenjaText.setActivated(false);
                showTimePicker();
            }
        });

        // Postavljanje klika na tekst za unos datuma
        datumHranjenjaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrijemeHranjenjaText.setActivated(false);
                datumHranjenjaText.setActivated(true);
                showDatePicker();
            }
        });

        // Postavljanje klika na dugme za postavljanje podsjetnika
        findViewById(R.id.podesiPodsjetnikButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajLjubimca();
            }
        });

        // Dodaj OnCheckedChangeListener za prekidač obavještenja


    }

    private void setupDateTimePickers() {
        // Postavljanje za TimePicker
        timePicker.setIs24HourView(true);

        // Postavljanje za DatePicker
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        // Inicijalizacija Calendar objekta
        selectedDateTime = Calendar.getInstance();
    }

    private void showTimePicker() {
        // Prikaži TimePicker dijalog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);

                        // Ažuriraj tekst s odab
                        // Ažuriraj tekst s odabranim vremenom
                        updateDateTimeText();
                    }
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void showDatePicker() {
        // Prikaži DatePicker dijalog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDateTime.set(Calendar.YEAR, year);
                        selectedDateTime.set(Calendar.MONTH, month);
                        selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Ažuriraj tekst s odabranim datumom
                        updateDateTimeText();
                    }
                },
                selectedDateTime.get(Calendar.YEAR),
                selectedDateTime.get(Calendar.MONTH),
                selectedDateTime.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDateTimeText() {
        // Ažuriraj tekst s odabranim datumom i vremenom
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String dateStr = dateFormat.format(selectedDateTime.getTime());
        String timeStr = timeFormat.format(selectedDateTime.getTime());

        // Provjeri koji odabir je aktivan i ažuriraj samo taj tekst
        if (vrijemeHranjenjaText.isActivated()) {
            vrijemeHranjenjaText.setText("Vrijeme hranjenja: " + timeStr);
        } else {
            datumHranjenjaText.setText("Datum hranjenja: " + dateStr);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void dodajLjubimca() {
        String ime = imeLjubimcaEditText.getText().toString();

        if (!ime.isEmpty()) {
            // Dodajte ljubimca u bazu
            Ljubimac noviLjubimac = new Ljubimac(ime, "12:00");

            dbHelper.dodajLjubimca(noviLjubimac);

            // Spremi podsjetnik samo ako su obavještenja uključena
            if (obavjestenjeSwitch.isChecked()) {
                postaviPodsjetnik(noviLjubimac);
            }

            // Prikaži poruku o uspješnom postavljanju podsjetnika
            Toast.makeText(this, "Podsjetnik je uspješno postavljen za " + ime, Toast.LENGTH_SHORT).show();
        }
    }

    private void postaviPodsjetnik(Ljubimac ljubimac) {
        // Provjeri je li obavještenje uključeno prije postavljanja podsjetnika
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean notificationsEnabled = settings.getBoolean(NOTIFICATIONS_ENABLED_KEY, true);

        if (notificationsEnabled) {

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
            intent.putExtra("imeLjubimca", ljubimac.getIme());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            long reminderTime = selectedDateTime.getTimeInMillis();

            // Postavi podsjetnik
            alarmManager.set(AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent);
        } else {
            showToast("Obavještenja su isključena. Podsjetnik nije postavljen.");
        }
    }
}
