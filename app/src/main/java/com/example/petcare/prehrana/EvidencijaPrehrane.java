package com.example.petcare.prehrana;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
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

    Button addpodsjetnik;

    EditText workeremail;

    public static final String DATABASE_NAME = "Prehrana.db";

    SQLiteDatabase mDatabase;

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

        workeremail = findViewById(R.id.imeLjubimcaEditText);

        addpodsjetnik = findViewById(R.id.podesiPodsjetnikButton);

        calendar = Calendar.getInstance();

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();

        // Postavljanje klika za odabir datuma i vremena
        datumVrijemeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        addpodsjetnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = workeremail.getText().toString().trim();

                if (email.isEmpty()) {
                    showToast("Molimo vas da unesete sve podatke.");
                } else {
                    // Svi potrebni podaci su uneseni, možete izvršiti unos u bazu podataka

                    SimpleDateFormat databaseDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                    String formattedDateTime = databaseDateFormat.format(calendar.getTime());

                    String insertSQL = "INSERT INTO Prehrana2 \n" +
                            "(Email, Vrijeme)\n" +
                            "VALUES \n" +
                            "(?, ?);";

                    mDatabase.execSQL(insertSQL, new String[]{email, formattedDateTime});

                    // Postavljanje push notifikacije
                    scheduleNotification(getNotification("Vaš podsjetnik"), calendar.getTimeInMillis());

                    Intent intent = new Intent(EvidencijaPrehrane.this, Prehrana.class);
                    startActivity(intent);
                }
            }
        });
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

    private void showToast(String message) {
        Toast.makeText(EvidencijaPrehrane.this, message, Toast.LENGTH_SHORT).show();
    }

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

    private void scheduleNotification(Notification notification, long delay) {
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            CharSequence channelName = "YOUR_CHANNEL_NAME";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            builder = new Notification.Builder(this, channelId);
        } else {
            builder = new Notification.Builder(this);
        }

        builder.setContentTitle("Vaš podsjetnik");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.notificationicon); // Dodajte ikonu notifikacije
        return builder.build();
    }
}
