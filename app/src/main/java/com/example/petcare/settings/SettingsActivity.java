package com.example.petcare.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;
import com.example.petcare.appintro.AppIntro;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NOTIFICATIONS_ENABLED_KEY = "notifications_enabled";

    private Switch notificationsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
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

        TextView aboutAppTextView = findViewById(R.id.aboutAppTextView);

        // Postavi OnClickListener za "O aplikaciji"
        aboutAppTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokreni novu aktivnost kad korisnik klikne "O aplikaciji"
                Intent intent = new Intent(SettingsActivity.this, AboutApp.class);
                startActivity(intent);
            }
        });

        TextView instructionsTextView = findViewById(R.id.instructionsTextView);
        instructionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokreće AppIntroActivity kada korisnik klikne na "Upute"
                Intent intent = new Intent(SettingsActivity.this, AppIntro.class);
                startActivity(intent);
            }
        });

        // Inicijalizacija prekidača za obavještenja
        notificationsSwitch = findViewById(R.id.notificationsSwitch);

        // Postavi OnCheckedChangeListener za prekidač
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Spremi trenutno stanje prekidača u SharedPreferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(NOTIFICATIONS_ENABLED_KEY, isChecked);
            editor.apply();

            // Prikazi poruku o tome jesu li obavještenja uključena ili isključena
            if (isChecked) {
                showToast("Obavještenja su uključena.");
            } else {
                showToast("Obavještenja su isključena.");
            }
        });

        // Učitaj trenutno stanje prekidača iz SharedPreferences i postavi ga
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean notificationsEnabled = settings.getBoolean(NOTIFICATIONS_ENABLED_KEY, true);
        notificationsSwitch.setChecked(notificationsEnabled);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
