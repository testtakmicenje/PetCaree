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




public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
            if ("Psi".equals(category)) {
                listView = findViewById(R.id.listViewDogs);
            } else if ("Mačke".equals(category)) {
                listView = findViewById(R.id.listViewCats);
            } else if ("Ptice".equals(category)) {
                listView = findViewById(R.id.listViewBirds);
            } else if ("Glodavci".equals(category)) {
                listView = findViewById(R.id.listViewRodents);
            } else if ("Ribe".equals(category)) {
                listView = findViewById(R.id.listViewFishes);
            } else {
                Toast.makeText(this, "Nepoznata kategorija", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            ArrayList<ListItem> dataList = getDataForCategory(category);
            CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, dataList);


            listView.setVisibility(View.VISIBLE);


            listView.setAdapter(adapter);



        } else {
            Toast.makeText(this, "Nije odabrana kategorija", Toast.LENGTH_SHORT).show();
            finish();
        }
    }




    private ArrayList<ListItem> getDataForCategory(String category) {
        ArrayList<ListItem> dataList = new ArrayList<>();

        if ("Psi".equals(category)) {
            dataList.add(new ListItem("Pudle", R.drawable.pudla));
            dataList.add(new ListItem("Kavalirski kral Charles", R.drawable.pas2));
            dataList.add(new ListItem("Francuski buldozi", R.drawable.pas3));
            dataList.add(new ListItem("Džek Rasel terijeri", R.drawable.pas4));
            dataList.add(new ListItem("Maltezeri", R.drawable.pas5));
            dataList.add(new ListItem("Ši-cu", R.drawable.pas6));
            dataList.add(new ListItem("Bostonski terijer ", R.drawable.pas7));
            dataList.add(new ListItem("Bichon Frise", R.drawable.pas8));
            dataList.add(new ListItem("Šetlandski ovčar", R.drawable.pas9));
            dataList.add(new ListItem("Pekinezer ", R.drawable.pas10));
        } else if ("Mačke".equals(category)) {
            dataList.add(new ListItem("Perzijska mačka", R.drawable.macka1));
            dataList.add(new ListItem("Sijamska mačka", R.drawable.macka2));
            dataList.add(new ListItem("Maine Coon", R.drawable.macka3));
            dataList.add(new ListItem("Britanska kratkodlaka mačka", R.drawable.macka4));
            dataList.add(new ListItem("Sibirska mačka", R.drawable.macka5));
            dataList.add(new ListItem("Ragdoll mačka", R.drawable.macka6));
            dataList.add(new ListItem("Egipatska Mau mačka", R.drawable.macka7));
            dataList.add(new ListItem("Abyssinian mačka", R.drawable.macka8));
            dataList.add(new ListItem("Burmanska mačka", R.drawable.macka9));
            dataList.add(new ListItem("Scottish Fold mačka", R.drawable.macka10));
        } else if ("Ptice".equals(category)) {
            dataList.add(new ListItem("Kanarinke", R.drawable.ptica1));
            dataList.add(new ListItem("Papagaj Nimfa", R.drawable.ptica2));
            dataList.add(new ListItem("Agapornis", R.drawable.ptica3));
            dataList.add(new ListItem("Budgerigar", R.drawable.ptica4));
            dataList.add(new ListItem("Kakoš", R.drawable.ptica5));
            dataList.add(new ListItem("Kardinal", R.drawable.ptica6));
            dataList.add(new ListItem("Ljubimac ljubičast", R.drawable.ptica7));
            dataList.add(new ListItem("Zebrice", R.drawable.ptica8));
            dataList.add(new ListItem("Finci", R.drawable.ptica9));
            dataList.add(new ListItem("Japanska ševa", R.drawable.ptica10));
        } else if ("Glodavci".equals(category)) {
            dataList.add(new ListItem("Hrčak", R.drawable.glodavac1));
            dataList.add(new ListItem("Zec", R.drawable.glodavac2));
            dataList.add(new ListItem("Kunić ", R.drawable.glodavac3));
            dataList.add(new ListItem("Kunić ", R.drawable.glodavac4));
            dataList.add(new ListItem("Miševi", R.drawable.glodavac5));
            dataList.add(new ListItem("Gerbili", R.drawable.glodavac6));
            dataList.add(new ListItem("Degu", R.drawable.glodavac7));
            dataList.add(new ListItem("Prerijski pas", R.drawable.glodavac8));
            dataList.add(new ListItem("Hrčak roborovski", R.drawable.glodavac9));
            dataList.add(new ListItem("Činčile", R.drawable.glodavac10));
        } else if ("Ribe".equals(category)) {
            dataList.add(new ListItem("Zlatna ribica", R.drawable.riba1));
            dataList.add(new ListItem("Betta riba", R.drawable.riba2));
            dataList.add(new ListItem("Moli riba ", R.drawable.riba3));
            dataList.add(new ListItem("Gupi riba ", R.drawable.riba4));
            dataList.add(new ListItem("Neonska riba", R.drawable.riba5));
            dataList.add(new ListItem("Danio ribe", R.drawable.riba6));
            dataList.add(new ListItem("Anđeoska riba", R.drawable.riba7));
            dataList.add(new ListItem("Diskus riba", R.drawable.riba8));
            dataList.add(new ListItem("Platy", R.drawable.riba9));
            dataList.add(new ListItem("Sumatranski borac", R.drawable.riba10));
        } else {
            Toast.makeText(this, "Nepoznata kategorija", Toast.LENGTH_SHORT).show();
            finish();
        }
        return dataList;
    }

}
