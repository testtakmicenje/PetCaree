package com.example.petcare;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home5Activity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home5_activity);


        expandableListView = findViewById(R.id.expandableListView);


        List<String> tipoviSavjeta = new ArrayList<>();
        tipoviSavjeta.add("Niske temperature");
        tipoviSavjeta.add("Visoke temperature");
        tipoviSavjeta.add("Troškovi");
        tipoviSavjeta.add("Mir kućnih ljubimaca");
        tipoviSavjeta.add("Socijalizacija životinja");
        tipoviSavjeta.add("Pravilna ishrana");
        tipoviSavjeta.add("Redovna veterinarska briga");
        tipoviSavjeta.add("Fizička aktivnost");
        tipoviSavjeta.add("Higijena životinja");
        tipoviSavjeta.add("Sigurnost životinja");



        HashMap<String, List<String>> savjetiMap = new HashMap<>();
        List<String> savjetiTip1 = new ArrayList<>();
        savjetiTip1.add("Osigurajte ljubimcima topao i suv prostor za boravak, kao što je unutrašnjost kuće ili sklonište. Prilagodite ishranu životinjama tako da imaju dovoljno kalorija da se zagriju. Ako je moguće, ograničite njihovo vrijeme provedeno napolju i izbjegavajte šetnje tokom najhladnijih dijelova dana. Ako imate kućne ljubimce koji žive napolju, pružite im izolaciju, kao što su tople prostirke, ćebad ili kućice. Redovno provjeravajte njihove šape i uši na znakove smrzavanja ili ozeblina.");
        savjetiMap.put("Niske temperature", savjetiTip1);

        List<String> savjetiTip2 = new ArrayList<>();
        savjetiTip2.add("Obezbijedite im dovoljno svježe vode i redovno ih hidrirajte tokom dana. Prilagodite njihovu ishranu tako da bude lagana i bogata vodom, kao što su voće i povrće. Osigurajte im hladovinu i zaštitu od sunca, kao što su sjenoviti dijelovi dvorišta ili klimatizovani prostori. Izbjegavajte šetnje tokom najtoplijeg dijela dana i radije ih izvodite rano ujutro ili kasno popodne. Pazite na znakove pregrijevanja kao što su pretjerano disanje ili povraćanje. Ako primjetite ove simptome, odmah se obratite veterinaru. Nikad ne ostavljajte životinje zatvorene u parkiranom automobilu, jer je se temperatura u vozilu može brzo povećati i postati opasna po njihovu sigurnost");
        savjetiMap.put("Visoke temperature", savjetiTip2);

        List<String> savjetiTip3 = new ArrayList<>();
        savjetiTip3.add("Kada razmišljaš o troškovima kućnih ljubimaca, treba uzeti u obzir nekoliko stvari. Prvo, hrana za ljubimce može biti značajan trošak, pa je važno planirati budžet za to. Također, veterinarske usluge poput redovnih pregleda i liječenja mogu biti skupi. Ne zaboravi na potrepštine poput povodaca, posuda za hranu i igračaka. Osim toga, treba razmotriti i druge troškove poput  eventualnih hitnih veterinarskih intervencija. Važno je biti svjestan ovih troškova i planirati unaprijed kako bi se osiguralo da možeš pružiti najbolju skrb svom ljubimcu. ");
        savjetiMap.put("Troškovi", savjetiTip3);

        List<String> savjetiTip4 = new ArrayList<>();
        savjetiTip4.add("Pokušajte smanjiti izvore buke u okolini  kako bi životinje imali tiši i mirniji prostor. Pokušajte održati rutinu za hranjenje, spavanje i igru kako bi se osjećali sigurno i ugodno. Osigurajte im mjesto u kući gdje se mogu povući i imati svoj prostor za odmor. Naučite prepoznati znakove stresa ili nelagode kod životinja i prilagodite okruženje kako bi im pružili mir.");
        savjetiMap.put("Mir kućnih ljubimaca", savjetiTip4);

        List<String> savjetiTip5 = new ArrayList<>();
        savjetiTip5.add("Kada se radi o socijalizaciji životinja, važno je imati na umu nekoliko ključnih stvari. Prvo, počnite polako da uvodite životinju u nove situacije i druge životinje. Dajte im dovoljno vremena da se prilagode i neka se osjećaju sigurno. Također, koristite pozitivno pojačanje i nagrađujte dobro ponašanje kako biste postaknuli pozitivnu interakciju. Budite strpljivi i pažljivo pratite njihove reakcije kako biste prepoznali znakove nelagode. Ako primijetite da se životinja osjeća nelagodno ili prestrašeno, prekinite interakciju i pokušajte ponovno kasnije.");
        savjetiMap.put("Socijalizacija životinja", savjetiTip5);

        List<String> savjetiTip6 = new ArrayList<>();
        savjetiTip6.add("Kada je u pitanju ishrana kućnih ljubimaca, važno je pružiti im uravnoteženu i hranjivu prehranu. Obratite pažnju na vrstu hrane koju dajete svom ljubimcu - možete se posavjetovati s veterinarom o najboljoj opciji za njihovu vrstu i veličinu. Također, pratite preporučene količine hrane i raspored obroka kako biste održali zdravu težinu. Ne zaboravite uvijek osigurati svježu vodu za piće. ");
        savjetiMap.put("Pravilna ishrana", savjetiTip6);

        List<String> savjetiTip7 = new ArrayList<>();
        savjetiTip7.add("Redovna veterinarska briga je izuzetno važna za zdravlje i dobrobit životinja. Veterinari su stručnjaci koji pružaju medecinsku njegu i savjete o zdravlju životinja. Redovne posjete veterinaru omogućavaju praćenje zdravstvenog stanja životinje, prevenciju bolesti, vakcinaciju i liječenje. Veterinar će pregledati životinju, provjeriti vitalne znakove, dati potrebne vakcine i preporučiti preventivne mjere. Također veterinar može pružiti savjete o ishrani, fizičkoj aktivnosti i opštj dobrobiti životinje.");
        savjetiMap.put("Redovna veterinarska briga", savjetiTip7);

        List<String> savjetiTip8 = new ArrayList<>();
        savjetiTip8.add("Fizička aktivnost je također važna za zdravlje životinja, baš kao i za nas ljude! Aktivnost pomaže održavanju zdrave težine, jača mišiće i kosti te poboljšava opštu kondiciju. Psi, na primjer, trebaju svakodnevno šetanje i igru kako bi iskoristili svoju energiju. Mačke se vole igrati i penjati, pa im osigurajte dovoljno prostora i igračaka. Za manje kućne ljubimce poput kunića ili hrčaka, možete im osigurati prostor za trčanje i igru. Uživajte u zajedničkim aktivnostima sa svojim ljubimcem, to je sjajan način za jačanje veze između vas!");
        savjetiMap.put("Fizička aktivnost", savjetiTip8);

        List<String> savjetiTip9 = new ArrayList<>();
        savjetiTip9.add("Redovno kupanje, četkanje i čišćenje zuba pomažu u održavanju čistog i zdravog krzna ili perja. Također, redovno čišćenje ušiju, očiju i kandži je važno za prevenciju infekcija. Za kućne ljubimce koji žive unutra, redovito čišćenje prostora u kojem borave je također bitno.");
        savjetiMap.put("Higijena životinja", savjetiTip9);

        List<String> savjetiTip10 = new ArrayList<>();
        savjetiTip10.add("Pazite da životinje budu smještene u sigurnom i ograđenom prostoru kako bi se spriječio bijeg ili ozljeđivanje. Uklonite potencijalno opasne predmete ili tvari iz okoline kako bi životinje izbjegle ozljede ili trovanja. Kada šetate sa svojim ljubimcem, držite ga na povodcu kako bi se izbjeglo trčanje u prometu ili ulazak u opasne situacije.");
        savjetiMap.put("Sigurnost životinja", savjetiTip10);
        adapter = new CustomExpandableListAdapter(tipoviSavjeta, savjetiMap);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedSavjet = (String) adapter.getChild(groupPosition, childPosition);
                Toast.makeText(Home5Activity.this, selectedSavjet, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private class CustomExpandableListAdapter extends BaseExpandableListAdapter {
        private List<String> tipoviSavjeta;
        private HashMap<String, List<String>> savjetiMap;

        CustomExpandableListAdapter(List<String> tipoviSavjeta, HashMap<String, List<String>> savjetiMap) {
            this.tipoviSavjeta = tipoviSavjeta;
            this.savjetiMap = savjetiMap;
        }

        @Override
        public int getGroupCount() {
            return tipoviSavjeta.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return savjetiMap.get(tipoviSavjeta.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return tipoviSavjeta.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return savjetiMap.get(tipoviSavjeta.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.expandable_group, null);
            }
            TextView textView = convertView.findViewById(R.id.groupTitle);
            textView.setText((String) getGroup(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.expandable_child, null);
            }
            TextView textView = convertView.findViewById(R.id.childText);
            textView.setText((String) getChild(groupPosition, childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}