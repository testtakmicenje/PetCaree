package com.example.petcare;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Zanimljivost2 extends AppCompatActivity {
        private Button dugmePrelazak;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.zanimljivost2);
            ImageView imageView = findViewById(R.id.logoImageView10);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed();
                }
            });


            Toolbar toolbar = findViewById(R.id.toolbar10);
            setSupportActionBar(toolbar);
            dugmePrelazak = findViewById(R.id.dugmeSledecaZanimljivost2);

            dugmePrelazak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Zanimljivost2.this, Zanimljivost3.class);


                    startActivity(intent);
                }
            });


        }
    }


