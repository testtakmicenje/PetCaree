package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Zanimljivost5 extends AppCompatActivity {
    private Button dugmePrelazak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zanimljivost5);
        ImageView imageView = findViewById(R.id.logoImageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        dugmePrelazak = findViewById(R.id.dugmeSledecaZanimljivost5);

        dugmePrelazak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Zanimljivost5.this, Zanimljivost6.class);


                startActivity(intent);
            }
        });

    }
}
