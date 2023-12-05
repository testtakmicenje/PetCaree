package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NewActivity3 extends AppCompatActivity {
    private List<WeightEntry> weightList;
    private WeightListAdapter adapter;
    private DatabaseHelper dbHelper; // pretpostavljamo da imate ovu klasu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new3_activity);
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

        ListView weightListView = findViewById(R.id.weightListView);
        Button addWeightButton = findViewById(R.id.addWeightButton);

        dbHelper = new DatabaseHelper(this);
        weightList = dbHelper.getAllWeights();

        // Inicijalizirajte adapter s dbHelper
        adapter = new WeightListAdapter(this, R.layout.weight_entry_item, weightList, dbHelper);

        weightListView.setAdapter(adapter);

        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otvorite novi ekran za unos težine
                Intent intent = new Intent(NewActivity3.this, AddWeightActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Ako je rezultat iz AddWeightActivity, osvježite popis težina
        if (requestCode == 1 && resultCode == RESULT_OK) {
            weightList.clear();
            weightList.addAll(dbHelper.getAllWeights());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Fetch the latest data from the database
        List<WeightEntry> updatedList = dbHelper.getAllWeights();

        // Clear the existing data in the list
        weightList.clear();

        // Add the updated data to the list
        weightList.addAll(updatedList);

        // Notify the adapter about the changes
        adapter.notifyDataSetChanged();
    }
}


