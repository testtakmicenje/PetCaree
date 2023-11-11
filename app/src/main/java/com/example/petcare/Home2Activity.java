package com.example.petcare;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;


public class Home2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2_activity);
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



        getSupportActionBar().setDisplayShowTitleEnabled(false);


        TextView textKrvarenje = findViewById(R.id.textKrvarenje);
        TextView textVestackoDisanje = findViewById(R.id.textVestackoDisanje);
        TextView textTrovanje = findViewById(R.id.textTrovanje);
        TextView textGusenje = findViewById(R.id.textGusenje);
        TextView textOpekotine = findViewById(R.id.textOpekotine);
        TextView textPovredaKostijuZglobova = findViewById(R.id.textPovredaKostijuZglobova);
        TextView textPovredaOciju = findViewById(R.id.textPovredaOciju);
        TextView textPovredaSapaNoktiju = findViewById(R.id.textPovredaSapaNoktiju);

        textKrvarenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, KrvarenjeActivity.class);
                startActivity(intent);
            }
        });

        textVestackoDisanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, VjestackoDisanjeActivity.class);
                startActivity(intent);
            }
        });

        textTrovanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, TrovanjeActivity.class);
                startActivity(intent);
            }
        });

        textGusenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, GusenjeActivity.class);
                startActivity(intent);
            }
        });

        textOpekotine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, OpekotineActivity.class);
                startActivity(intent);
            }
        });

        textPovredaKostijuZglobova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, PovredaKostijuActivity.class);
                startActivity(intent);
            }
        });

        textPovredaOciju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, PovredaOcijuActivity.class);
                startActivity(intent);
            }
        });

        textPovredaSapaNoktiju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, PovredaSapaActivity.class);
                startActivity(intent);
            }
        });
        Button btnPrepoznavanjeSimptoma = findViewById(R.id.btnPrepoznavanjeSimptoma);


        btnPrepoznavanjeSimptoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, PrepoznavanjeSimptomaActivity.class);
                startActivity(intent);
            }
        });
    }
}




