package com.example.petcare.settings;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;
import com.example.petcare.appintro.AppIntro;


public class SettingsActivity extends AppCompatActivity {

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

        // Ostatak koda za postavljanje postavki...
    }
}
