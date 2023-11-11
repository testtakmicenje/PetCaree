package com.example.petcare;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
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
        int slikaResId = intent.getIntExtra("slika", 0); // 0 je default vrednost ako ne postoji
        String tekst = intent.getStringExtra("tekst");


        RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout); // Promenio ovde
        backgroundLayout.setBackgroundResource(slikaResId); // Postavi pozadinu na slikaResId

        TextView textView = findViewById(R.id.textView);
        textView.setText(tekst);
    }
}
