package com.example.petcare.mojiljubimci;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import com.example.petcare.R;
import android.view.View;
import java.util.List;
import android.widget.Button;

public class MyPetsActivity extends AppCompatActivity {

    private RecyclerView petsRecyclerView;
    private PetListAdapter petListAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypets_activity);

        petsRecyclerView = findViewById(R.id.petstRecyclerView);
        databaseHelper = new DatabaseHelper(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        petsRecyclerView.setLayoutManager(layoutManager);

        // Dobavite listu ljubimaca iz baze podataka
        List<Pet> petList = databaseHelper.getAllPets();

        // Postavljanje adaptera za RecyclerView
        petListAdapter = new PetListAdapter(this, petList);

        // Postavljanje adaptera za RecyclerView
        petsRecyclerView.setAdapter(new PetListAdapter(this, petList));

        Button addWeightButton = findViewById(R.id.addWeightButton);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPetsActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });
    }
}
