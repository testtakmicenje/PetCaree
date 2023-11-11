package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;




import androidx.appcompat.app.AppCompatActivity;

public class Zanimljivost11 extends AppCompatActivity {
    private Button dugmePrelazak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zanimljivost11);
        ImageView imageView = findViewById(R.id.logoImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dugmePrelazak = findViewById(R.id.dugmeSledecaZanimljivost11);

        dugmePrelazak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Zanimljivost11.this, HomeActivity.class);


                startActivity(intent);
            }
        });




    }
}
