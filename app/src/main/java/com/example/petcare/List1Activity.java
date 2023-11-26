package com.example.petcare;
import android.widget.AdapterView;
import java.util.HashMap;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;




public class List1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);

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
        if (intent != null && intent.hasExtra("CATEGORY")) {
            final String category = intent.getStringExtra("CATEGORY");

            ListView listView;
            if ("Zmije".equals(category)) {
                listView = findViewById(R.id.listView1);
            } else if ("Gušteri".equals(category)) {
                listView = findViewById(R.id.listView2);
            } else if ("Ptice grabljivice".equals(category)) {
                listView = findViewById(R.id.listView3);
            } else if ("Egzotične ribe".equals(category)) {
                listView = findViewById(R.id.listView4);
            } else if ("Paukovi".equals(category)) {
                listView = findViewById(R.id.listView5);
            } else {
                Toast.makeText(this, "Nepoznata kategorija", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            ArrayList<ListItem> dataList = getDataForCategory(category);
            CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, dataList);


            listView.setVisibility(View.VISIBLE);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListItem selectedItem = dataList.get(position);
                    showDetailsActivity(selectedItem);
                }
            });


        } else {
            Toast.makeText(this, "Nije odabrana kategorija", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private ArrayList<ListItem> getDataForCategory(String category) {
        ArrayList<ListItem> dataList = new ArrayList<>();

    if ("Zmije".equals(category)) {
        dataList.add(new ListItem("Kraljevska pitonka ", R.drawable.zmija1,""));
        dataList.add(new ListItem("Boa konstriktor", R.drawable.zmija2,""));
        dataList.add(new ListItem("Zelena pitonka  ", R.drawable.zmija3,""));
        dataList.add(new ListItem("Kornjačarska zmija  ", R.drawable.zmija4,""));
        dataList.add(new ListItem("Milk Snake", R.drawable.zmija5,""));
        dataList.add(new ListItem("Kalifornijski kraljevski zmaj ", R.drawable.zmija6,""));
        dataList.add(new ListItem("Žuta Anaconda", R.drawable.zmija7,""));
        dataList.add(new ListItem("Tigrasti piton", R.drawable.zmija8,""));
        dataList.add(new ListItem("Kukuruzni smuk", R.drawable.zmija9,""));
        dataList.add(new ListItem("Podvezica zmija", R.drawable.zmija10,""));
    } else if ("Gušteri".equals(category)) {
        dataList.add(new ListItem("Leopard gušter", R.drawable.guster1,""));
        dataList.add(new ListItem("Bartagame", R.drawable.guster2,""));
        dataList.add(new ListItem("Skink ", R.drawable.guster3,""));
        dataList.add(new ListItem("Anolis ", R.drawable.guster4,""));
        dataList.add(new ListItem("Zelena iquana", R.drawable.guster5,""));
        dataList.add(new ListItem("Kameleon", R.drawable.guster6,""));
        dataList.add(new ListItem("Panter kameleon", R.drawable.guster7,""));
        dataList.add(new ListItem("Skelton guster ", R.drawable.guster8,""));
        dataList.add(new ListItem("Krastavac gušter", R.drawable.guster9,""));
        dataList.add(new ListItem("Blue Tree Monitor", R.drawable.guster10,""));
     } else if ("Ptice grabljivice".equals(category)) {
        dataList.add(new ListItem("Jastreb", R.drawable.grabljivica1,""));
        dataList.add(new ListItem("Sova", R.drawable.grabljivica2,""));
        dataList.add(new ListItem("Jastreb Kestenjača ", R.drawable.grabljivica3,""));
        dataList.add(new ListItem("Kea ", R.drawable.grabljivica4,""));
        dataList.add(new ListItem("Jastreb soko", R.drawable.grabljivica5,""));
        dataList.add(new ListItem("Sovuljaga ", R.drawable.grabljivica6,""));
        dataList.add(new ListItem("Jastreb Crvenonogi", R.drawable.grabljivica7,""));
        dataList.add(new ListItem("Jastreb Gusar", R.drawable.grabljivica8,""));
        dataList.add(new ListItem("Jastreb Kokošinjac", R.drawable.grabljivica9,""));
        dataList.add(new ListItem("Sova Sneško", R.drawable.grabljivica10,""));
    } else if ("Egzotične ribe".equals(category)) {
            dataList.add(new ListItem("Archer Fish ", R.drawable.ribaa1,""));
            dataList.add(new ListItem("Dragon Wrasse Arapaima ", R.drawable.ribaa2,""));
            dataList.add(new ListItem("Puffer fish  ", R.drawable.ribaa3,""));
            dataList.add(new ListItem("Axolotl  ", R.drawable.ribaa4,""));
            dataList.add(new ListItem("Mbu Puffer ", R.drawable.ribaa5,""));
            dataList.add(new ListItem("Rainbowfish ", R.drawable.ribaa6,""));
            dataList.add(new ListItem("Mandarinfish ", R.drawable.ribaa7,""));
            dataList.add(new ListItem("Arowana ", R.drawable.ribaa8,""));
            dataList.add(new ListItem("Electric Blue Acara ", R.drawable.ribaa9,""));
            dataList.add(new ListItem("Arapaima", R.drawable.ribaa10,""));
        } else if ("Paukovi".equals(category)) {
            dataList.add(new ListItem("Chilean Rose Hair Tarantula ", R.drawable.pauk1,""));
            dataList.add(new ListItem("Mexican Redknee Tarantula ", R.drawable.pauk2,""));
            dataList.add(new ListItem("Brazilian Black Tarantula  ", R.drawable.pauk3,""));
            dataList.add(new ListItem("Pink Toe Tarantula  ", R.drawable.pauk4,""));
            dataList.add(new ListItem("Goliath Birdeater ", R.drawable.pauk5,""));
            dataList.add(new ListItem("Curly Hair Tarantula ", R.drawable.pauk6,""));
            dataList.add(new ListItem("Costa Rican Zebra Tarantula ", R.drawable.pauk7,""));
            dataList.add(new ListItem("Mexican Red Leg Tarantula ", R.drawable.pauk8,""));
            dataList.add(new ListItem("Arizona Blonde Tarantula ", R.drawable.pauk9,""));
            dataList.add(new ListItem("Greenbottle Blue Tarantula ", R.drawable.pauk10,""));
        } else {
            Toast.makeText(this, "Nepoznata kategorija", Toast.LENGTH_SHORT).show();
            finish();
        }
        return dataList;
    }

    private void showDetailsActivity(ListItem selectedItem) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("imageResource", selectedItem.getImageResourceId());
        intent.putExtra("text", selectedItem.getName());
        intent.putExtra("description", selectedItem.getDescription());


        startActivity(intent);
    }

}
