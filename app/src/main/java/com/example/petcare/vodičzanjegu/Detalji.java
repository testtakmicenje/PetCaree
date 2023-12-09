package com.example.petcare.vodičzanjegu;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petcare.R;

public class Detalji extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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



        Intent intent = getIntent();
        int slikaResId = intent.getIntExtra("slika", 0);
        String tekst = intent.getStringExtra("tekst");


        RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        backgroundLayout.setBackgroundResource(slikaResId);

        TextView textView = findViewById(R.id.textView);
        textView.setText(tekst);
    }
}