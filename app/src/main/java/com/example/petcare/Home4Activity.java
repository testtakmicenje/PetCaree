package com.example.petcare;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Home4Activity extends AppCompatActivity {
    private Button dugmePrelazak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4_activity);
        ImageView imageView = findViewById(R.id.logoImageView11);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        dugmePrelazak = findViewById(R.id.dugmeSledecaZanimljivost);

        dugmePrelazak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home4Activity.this, Zanimljivost2.class);


                startActivity(intent);
            }
        });
    }
}





