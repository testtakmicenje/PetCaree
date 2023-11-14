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

        Intent intent = getIntent();
        int slikaResId = intent.getIntExtra("imageResource", 0); // Use "imageResource" key
        String tekst = intent.getStringExtra("text");
        String description = intent.getStringExtra("description");

        RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        backgroundLayout.setBackgroundResource(slikaResId);

        // Now, you can use the "description" string to display additional information if needed
        TextView descriptionTextView = findViewById(R.id.textView);
        descriptionTextView.setText(description);
    }
}

