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
    private static final String TOAST_SHOWN_KEY = "toast_shown";

    private Switch notificationsSwitch;
    private boolean lastNotificationStatus;
    private boolean toastShown;

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
            if (isChecked != lastNotificationStatus) {
                // Status se promijenio, spremi u SharedPreferences i pokaži toast ako već nije prikazan
                if (!toastShown) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(NOTIFICATIONS_ENABLED_KEY, isChecked);
                    editor.apply();

                    // Prikazi poruku o tome jesu li obavještenja uključena ili isključena
                    showToast(isChecked ? "Obavještenja su uključena." : "Obavještenja su isključena.");

                    // Postavi flag da je toast prikazan
                    toastShown = true;
                }

                // Ažuriraj zadnji status
                lastNotificationStatus = isChecked;
            }
        });

        // Učitaj trenutno stanje prekidača iz SharedPreferences i postavi ga
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean notificationsEnabled = settings.getBoolean(NOTIFICATIONS_ENABLED_KEY, true);
        notificationsSwitch.setChecked(notificationsEnabled);

        // Učitaj flag za prikazivanje toast poruke
        toastShown = settings.getBoolean(TOAST_SHOWN_KEY, false);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Spremi flag za prikazivanje toast poruke u SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(TOAST_SHOWN_KEY, toastShown);
        editor.apply();
    }
}
