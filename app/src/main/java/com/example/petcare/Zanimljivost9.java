package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Zanimljivost9 extends AppCompatActivity {
    private Button dugmePrelazak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zanimljivost9);
        ImageView imageView = findViewById(R.id.logoImageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        dugmePrelazak = findViewById(R.id.dugmeSledecaZanimljivost9);

        dugmePrelazak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Zanimljivost9.this, Zanimljivost10.class);


                startActivity(intent);
            }
        });

    }
}
