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
        dataList.add(new ListItem("Leopard gušter", R.drawable.guster1,"Lepard gušter\n\nLeopard gušter može narasti do dužine od 60 do 90 centimetara. Njegova karakteristična boja je smeđa sa tamnim mrljama koje podsećaju na leopardovu šaru.\n\nOvi gušteri su izuzetno brzi i okretni, što im pomaže u lovu na insekte i male životinje. Leopard gušteri su noćne životinje i veći deo dana provode sakriveni u rupama ili pukotinama stijena. Njihova ishrana se uglavnom sastoji od insekata, paukova, puževa i malih gmizavaca. Leopard gušteri imaju sposobnost da odbace svoj rep kao odbrambeni mehanizam kada su ugroženi.\n\nUkoliko se osećaju preplašeno ili ugroženo, mogu ispuštati neugodan miris iz analnih žlijezda. Ovi gušteri su prilično tih i rijetko proizvode zvukove. Leopard gušteri su prilagođeni sušnim uslovima i mogu preživjeti duže periode bez vode. Nažalost, leopard gušteri su ugrožena vrsta zbog gubitka staništa i ilegalne trgovine.\n"));
        dataList.add(new ListItem("Bartagame", R.drawable.guster2,"Bartagame\n\nBartagame gušter je vrsta agame koja potiče iz sušnih područja Srednje Azije. Njihova karakteristična boja može varirati od smeđe do sive, a na leđima imaju tamne pruge.\n\nOvi gušteri su prilično veliki i mogu narasti do dužine od 30 do 60 centimetara. Bartagame gušteri su biljojedi i njihova ishrana se uglavnom sastoji od povrća, voća i insekata. Imaju izuzetno snažne čeljusti i oštre zube kojima mogu lako usitniti hranu. Bartagame gušteri su danju aktivni, a noći provode spavajući ili odmarajući se na sigurnim mjestima. Oni su izuzetno društveni gmizavci i često se mogu vidjeti kako se sunčaju zajedno na stijenama.\n\nOvi gušteri imaju sposobnost da regulišu svoju tjelesnu temperaturu pomoću sunčeve svjetlosti. Bartagame gušteri su poznati po svom karakterističnom ponašanju, kao što je mašenje glave i širenje grla tokom upozoravanja ili parjenja. U divljini, Bartagame gušteri mogu živjeti do 10 godina, dok u zatočeništvu mogu živjeti i do 15 godina.\n"));
        dataList.add(new ListItem("Skink ", R.drawable.guster3,"Skink\n\nSkink gušteri su raznolika grupa gmizavaca koja obuhvata preko 1.500 vrsta. Oni se mogu naći širom svijeta, od tropskih šuma do pustinjskih područja.\n\nSkink gušteri su obično mali do srednje veličine, sa dužinom tijela koja varira od svega nekoliko centimetara do preko 30 centimetara. Njihova koža može biti različitih boja i uzoraka, od zemljanih tonova do svijetlih i šarenih kombinacija. Skink gušteri su vrlo agilni i brzi, često se penju po drveću i stijenama. Ovi gušteri su pretežno biljojedi, ali neki se hrane i insektima i drugim malim životinjama.\n\nSkink gušteri imaju sposobnost da se regenerišu, što znači da mogu ponovo izrasti izgubljene repove ili dijelove tijela. Oni koriste svoj rep kao balans prilikom penjanja i za odbranu od predatora. Skink gušteri su poznati po svom društvenom ponašanju i često se mogu vidjeti u grupama ili parovima.\n\nNeki skink gušteri su vrlo prilagodljivi i mogu živjeti u različitim staništima, uključujući i urbana područja.\n"));
        dataList.add(new ListItem("Anolis ", R.drawable.guster4,"Anolis\n\nAnolis gušteri su poznati po svojoj sposobnosti da promijene boju svoje kože kako bi se prilagodili okolini. Postoji preko 400 vrsta anolis guštera, koji se mogu naći u različitim dijelovima svijeta, posebno u Centralnoj i Južnoj Americi. Oni su obično mali do srednje veličine, sa dužinom tijela koja varira od 5 do 20 centimetara.\n\nAnolis gušteri su vrlo agilni i brzi, često se penju po granama drveća i skakuću sa jedne na drugu. Njihovi prsti su opremljeni ljepljivim jastučićima koji im pomažu da se čvrsto drže grana. Mladi anolis gušteri imaju tendenciju da budu zeleni, dok odrasli mogu biti različitih boja, uključujući smeđu, crvenu i plavu. Ovi gušteri se hrane insektima, poput mrava, skakavaca i leptira. Anolis gušteri su teritorijalni i često se bore za svoje stanište i partnere.\n\nOni imaju izuzetno dobar vid i mogu primjetiti i najmanje pokrete plijena ili predatora. Anolis gušteri su fascinantne životinje za proučavanje i mnogi naučnici ih koriste kao modele za istraživanje evolucije i adaptacije.\n"));
        dataList.add(new ListItem("Zelena iquana", R.drawable.guster5,"Zelena iguana\n\nZelene iguane su najveće vrste iguana i mogu narasti do 1,5 metara u dužinu. One su poznate po svojoj svijetlozelenoj boji koja im pomaže da se kamufliraju u okolini. Zelene iguane su biljojedi i njihova se uglavnom sastoji od lišća, voća i cvijetova.\n\nOne su odlični penjači i mogu se popeti na drveće visoko iznad tla. Zelene iguane imaju oštre kandže i snažan rep koji koriste za održavanje ravnoteže dok se penju. Njihov rep može biti duži od tijela i koristi se kao odbrambeni mehanizam protiv predatora. Zelene iguane su tropske životinje i vole toplu i vlažnu klimu. One su vrlo inteligentne i mogu se naučiti da prepoznaju svoje vlasnike. Zelene iguane mogu živjeti i do 20 godina ako imaju odgovarajuću brigu i ishranu.\n\nOve prelijepe životinje su popularni kućni ljubimci, ali zahtijevaju posebnu njegu i prostor za kretanje.\n"));
        dataList.add(new ListItem("Kameleon", R.drawable.guster6,"Kameleon\n\nKameleoni su poznati po svojoj sposobnosti mijenjanja boje kako bi se prilagodili okolini i komunicirali s drugim kameleonima. Postoje preko 160 različitih vrsta kameleona širom svijeta, a svaka ima svoje jedinstvene karakteristike. Kameleoni imaju izuzetno brzi jezik koji koriste za hvatanje plijena, poput insekata. Oni imaju oči koje se mogu neovisno okretati, omogućavajući im da istovremeno promatraju različite smjerove.\n\nKameleoni imaju sposobnost da se prilagode različitim vrstama okoline, uključujući šumske, pustinjske i planinske regije. Neki kameleoni imaju izdužene repove koje koriste za održavanje ravnoteže dok se penju po granama. Oni su vrlo mirni i spori reptili, često provodeći većinu vremena mirno sjedeći na granama drveća. Kameleoni su izvrsni lovci zahvaljujući svojim dugim i ljepljivim jezicima, kojima mogu uhvatiti plijen na udaljenosti.\n\nNeki kameleoni mogu mijenjati boju ne samo radi kamuflaže, već i izražavanja emocija poput straha ili uzbuđenja. Ovi jedinstveni reptili imaju posebno oblikovane stopala koja im pomažu da se čvrsto drže grana i penju se po vertikalnim površinama.\n"));
        dataList.add(new ListItem("Panter kameleon", R.drawable.guster7,"Panter kameleon\n\nPanter kameleon, poznat i kao Furcifer pardalis, je vrsta kameleona koja je endemska za Madagaskar. Ime \"panter\" dolazi od karakterističnih crnih mrlja koje podsjećaju na uzorak pantere na njihovom tijelu. Panter kameleon može mijenjati boje, ali njihova osnovna boja je obično zelena, koja im pomaže u kamuflaži među lišćem.\n\nMužjaci panter kameleona imaju veće i šarenije boje od ženki, a boje se često koriste za privlačenje partnera. Ovi kameleoni imaju izuzetno dug jezik koji mogu izbaciti kako bi uhvatili svoj plijen, poput insekata. Panter kameleoni imaju izuzetno dobar vid, s mogućnošću da vide široki spektar boja i detalja. Oni su vrlo prilagodljivi i mogu se prilagoditi različitim vrstama okoline, uključujući šumske i suhe regije. Panter kameleoni su izvrsni penjači i često se mogu vidjeti kako se penju po granama drveća.\n\nKao i ostali kameleoni, panter kameleoni polažu jaja koja se razvijaju izvan tijela ženke. Ovi kameleoni su važan dio ekosustava Madagaskara, igrajući ulogu u kontroli populacije insekata i oprašivanju biljaka.\n"));
        dataList.add(new ListItem("Skelton guster ", R.drawable.guster8,"Skelton gušter\n\nSkelton gušter, poznat i kao Skeletonema costatum, je vrsta morske alge koja se može naći u oceanima širom svijeta. Ova alga je jednostanični organizam koji ima karakterističan izgled, s tankim i dugim stanicama koje izgledaju kao kosti. Skelton gušter je važan dio morskog ekosustava, jer pruža hranu i stanište za različite vrste planktona i riba.\n\nOva alga može cvjetati u velikim brojevima i stvarati takozvane \"crvene plime\", što može imati negativan utjecaj na druge organizme u moru. Skelton gušter je fotosintetički organizam, što znači da koristi sunčevu svjetlost kako bi proizvela hranu. Ova alga je vrlo prilagodljiva i može preživjeti u različitim uvjetima, uključujući promjene temperature i saliniteta vode. Skelton guster može biti vrlo važan indikator kvalitete vode, jer reagira na promjene u okolišu. Njegova prisutnost ili odsutnost može ukazivati na različite ekološke uvjete u moru. Skelton guster je također poznat po tome što može proizvoditi toksine koji mogu biti štetni za druge organizme.\n\nZnanstvenici proučavaju skelton guster kako bi bolje razumjeli njegovu ulogu u morskom ekosustavu i kako bi se bolje upravljalo kvalitetom vode. \n"));
        dataList.add(new ListItem("Krastavac gušter", R.drawable.guster9,"Krastavac gušter\n\n Krastavac gušter, poznat i kao Chamaeleo cucumis, je vrsta guštera koji se može naći u istočnoj Africi. Ovi gušteri dobili su naziv \"krastavac\" zbog svoje zelene boje i izduženog tijela koje podsjeća na krastavac.\n\nOni imaju sposobnost mijenjanja boje kako bi se prilagodili okolini i sakrili od predatora. Krastavac gušteri imaju dug i ljigav jezik koji koriste za hvatanje insekata i drugih malih životinja. Njihove oči mogu se nezavisno kretati i rotirati, omogućavajući im da istražuju okolinu bez okretanja glave. Ovi gušteri su iznimno prilagodljivi i mogu se kretati po granama i lijanama s lakoćom. Krastavac gušteri su vrlo dobri u kamuflaži, koristeći svoju zelenu boju i teksturu kože kako bi se stopili s okolinom. Njihovi repovi su preklapajući, što im pomaže u ravnoteži dok se kreću po granama. Krastavac gušteri su vrlo mirni i oprezni gušteri, koji se često mogu vidjeti kako se polako kreću kroz vegetaciju u potrazi za hranom.\n\nOvi gušteri su važan dio svojih ekosustava jer se hrane insektima i pridonose održavanju ravnoteže u prirodi.\n"));
        dataList.add(new ListItem("Blue Tree Monitor", R.drawable.guster10,"Blue Tree Monitor\n\nBlue tree monitor gušter, poznat i kao Varanus macraei, je vrsta guštera koji se može naći na otocima Biak i Numfor u Indoneziji. Oni su poznati po svojoj prekrasnoj plavoj boji koja ih čini jedinstvenim među gušterima. Blue tree monitor gušteri su prilično rijetki i smatraju se ugroženom vrstom.\n\nOvi gušteri su prilagođeni životu u drveću i provode većinu svog vremena penjući se po granama. Imaju snažne kandže koje im pomažu da se lako penju i drže za grane. Blue tree monitor gušteri su izvrsni plivači i često se mogu vidjeti kako skaču u vodu i plivaju. Hrane se uglavnom insektima, voćem i manjim životinjama koje pronalaze u svojem okruženju. \n\nOvi gušteri su vrlo inteligentni i imaju dobar osjet vida i mirisa. Blue tree monitor gušteri su vrlo tajanstveni i oprezni, često se skrivaju među granama kako bi izbjegli opasnost. Njihova plava boja može varirati ovisno o dobi i raspoloženju, što ih čini još zanimljivijima za promatranje.\n"));
    } else if ("Ptice grabljivice".equals(category)) {
        dataList.add(new ListItem("Jastreb", R.drawable.grabljivica1,"Jastreb\n\nJastreb je ptica grabljivica koja ima oštar vid i kljun. Jastrebovi su izuzetno brzi i okretni u letu. Njihov let može biti elegantan i graciozan. Jastrebovi se hrane malim sisavcima, pticama i gmizavcima. Neki jastrebovi mogu imati raspon krila od preko metar.\n\nJastrebovi su poznati po svom izuzetnom lovačkom instinktu. Njihove oštre kandže im pomažu da uhvate plijen. Jastrebovi su rasprostranjeni širom svijeta, a neke vrste su zaštićene. Neki jastrebovi žive u šumama, dok drugi preferiraju otvorene prostore. Jastrebovi mogu imati različite boje perja, uključujući smeđu, sivu i bijelu.\n"));
        dataList.add(new ListItem("Sova", R.drawable.grabljivica2,"Sova\n\nSove su noćne ptice koje su poznate po svojoj sposobnosti da love u mraku. One imaju izuzetno oštar vid i izvrstan sluh. Sove imaju karakterističan oblik glave sa velikim, okruglim očima. Njihove oči su prilagođene za lov u mraku.\n\nSove se hrane malim sisavcima, pticama, insektima i gmizavcima. Neki sovinji kljunovi su zakrivljeni prema dolje, što im pomaže da uhvate plijen. Sove su tihe letačice i mogu letjeti gotovo nečujno. One imaju mekano perje koje smanjuje buku tokom leta. Sove su rasprostranjene širom svijeta, a neke vrste su prilagođene različitim staništima. Neki primjerci sova mogu imati raspon krila od preko metar.\n"));
        dataList.add(new ListItem("Jastreb Kestenjača ", R.drawable.grabljivica3,"Jastreb Kestenjača\n\nJastreb soko, poznat i kao soko, je vrsta grabljivice koja se može naći širom svijeta. Ovi jastrebovi su poznati po svojoj brzini i snazi u lovu na plijen. Jastreb soko ima karakteristično smeđe perje s bijelim prugama na trbuhu. Oni imaju oštar kljun i kandže koje koriste za hvatanje i ubijanje svoje hrane. Jastreb soko se hrani malim sisavcima, pticama i gmizavcima.\n\nOvi jastrebovi su izuzetno dobri letači i mogu doseći vrlo visoke brzine dok love plijen. Jastreb soko je poznat po svom visokom položaju u lancu ishrane i često je vrhunski predator u svom okruženju. Oni uglavnom žive u šumskim područjima, ali se mogu naći i u otvorenim prostorima. Jastreb soko je zaštićena vrsta u mnogim zemljama zbog smanjenja broja populacije.\n"));
        dataList.add(new ListItem("Kea ", R.drawable.grabljivica4,"Kea\n\nKea je vrsta papagaja koja je endemska za Novi Zeland. Ovi papagaji su poznati po svojoj inteligenciji i znatiželji. Kea ima zelenu boju perja s crvenom nijansom na donjem dijelu leđa. Oni imaju dug i zakrivljenkljun koji koriste za hranjenje. Kea je prilagođena životu u planinskim područjima i često se može naći u alpskim staništima.\n\nOvi papagaji su poznati po svojoj sposobnosti da otvaraju brave, torbe i istražuju različite predmete. Kea se hrani različitom hranom, uključujući voće, sjemenke, insekte i čak leševe. Ovi papagaji su društveni i često se mogu vidjeti u grupama. Kea je ugrožena vrsta zbog gubitka staništa i lova. Neki napori su poduzeti kako bi se zaštitila ova jedinstvena ptica.\n"));
        dataList.add(new ListItem("Jastreb soko", R.drawable.grabljivica5,"Jastreb soko\n\nJastreb soko, poznat i kao soko, je vrsta grabljivice koja se može naći širom svijeta. Ovi jastrebovi su poznati po svojoj brzini i snazi u lovu na plijen. Jastreb soko ima karakteristično smeđe perje s bijelim prugama na trbuhu. Oni imaju oštar kljun i kandže koje koriste za hvatanje i ubijanje svoje hrane.\n\nJastreb soko se hrani malim sisavcima, pticama i gmizavcima. Ovi jastrebovi su izuzetno dobri letači i mogu doseći vrlo visoke brzine dok love plijen. Jastreb soko je poznat po svom visokom položaju u lancu ishrane i često je vrhunski predator u svom okruženju. Oni uglavnom žive u šumskim područjima, ali se mogu naći i u otvorenim prostorima. Jastreb soko je zaštićena vrsta u mnogim zemljama zbog smanjenja broja populacije.\n"));
        dataList.add(new ListItem("Sovuljaga ", R.drawable.grabljivica6,"Sovuljaga\n\nSovuljaga, poznata i kao sova, je noćna ptica koja je često povezana s mudrošću. Ove ptice imaju karakteristične velike oči i okruglo lice. Sovuljage su prilagođene noćnom načinu života i imaju izuzetno dobar sluh i vid.\n\nOne se hrane malim sisavcima, pticama, insektima i gmizavcima. Sovuljage su poznate po svojoj sposobnosti da lete tiho i neprimjetno, što im pomaže u lovu na plijen. Ove ptice mogu biti različitih veličina i boja, ovisno o vrsti. Sovuljage često grade gnijezda u šupljinama drveća ili na visokim mjestima. One su simbol mudrosti i često se pojavljuju u mitologiji i književnosti. Sovuljage su fascinantne ptice koje nas oduševljavaju svojim tajanstvenim načinom života.\n"));
        dataList.add(new ListItem("Jastreb Crvenonogi", R.drawable.grabljivica7,"Jastreb Crvenonogi\n\nJastreb crvenonogi, poznat i kao jastreb s nogama crvene boje, je prekrasna vrsta jastreba koja se može naći u određenim dijelovima Sjeverne Amerike. Ovi jastrebovi imaju smeđe perje s crvenim nogama koje su im dale naziv.\n\nJastreb crvenonogi je snažan grabljivac i hrani se uglavnom manjim sisavcima, pticama i gmizavcima. Oni su izvrsni letači i koriste svoju brzinu i okretnost za lov na plijen. Jastreb crvenonogi je simbol snage i hrabrosti u mnogim kulturama. Njihov izgled i ponašanje ih čini jedinstvenim u svijetu ptica. Jastrebovi crvenonogi obično grade svoja gnijezda na stablima ili stijenama, visoko iznad tla.\n\nNjihove oštre kandže i snažan kljun omogućuju im da se uspješno bore za hranu i obrane svoj teritorij. Ovi jastrebovi često migriraju i putuju velike udaljenosti tijekom godine u potrazi za hranom i povoljnijim uvjetima za gniježđenje. Jastreb crvenonogi ima iznimno dobar vid koji mu pomaže u pronalaženju plijena čak i na velikim udaljenostima.\n"));
        dataList.add(new ListItem("Jastreb Gusar", R.drawable.grabljivica8,"Jatreb gusar\n\nJastreb gusar, poznat i kao jastreb sa crnim perjem i bijelim repom, je impresivna vrsta ptice grabljivice. Oni su poznati po svom karakterističnom izgledu, s crnim perjem na tijelu i bijelim perjem na repu. Jastrebovi gusari su izuzetno vješti letači i lovci. Oni koriste svoju oštru kandžu i snažan kljun da uhvate svoj plijen. Jastrebovi gusari se hrane raznim vrstama životinja, uključujući male sisavce, ptice i gmizavce. Ovi jastrebovi često žive u obalnim područjima i blizini vode, gdje mogu pronaći obilje hrane. Jastrebovi gusari su poznati po svom hrabrom i neustrašivom karakteru. Oni su simbol slobode i avanture.\n\nNjihov let kroz zrak je zaista impresivan i često privlači pažnju ljudi. Jastreb gusar je vrsta ptice grabljivice koja se često može vidjeti u obalnim područjima i močvarama. Njihovo crno perje i bijeli rep čine ih prepoznatljivim i atraktivnim za promatranje. Jastrebovi gusari su izuzetno vješti letači i mogu doseći velike brzine dok love plijen. Ovi jastrebovi su poznati po svojoj hrabrosti i sposobnosti da se bore protiv većih plijenova.\n"));
        dataList.add(new ListItem("Jastreb Kokošinjac", R.drawable.grabljivica9,"Jastreb kokošinjac\n\nJastreb kokošinjac, poznat i kao američki jastreb, je vrsta ptice grabljivice koja se često može naći u ruralnim područjima. Oni su prilično veliki jastrebovi s karakterističnim smeđim perjem i prugastim repom. Jastrebovi kokošinjci su poznati po tome što love manje životinje poput ptica, zečeva i miševa. Oni često napadaju kokošinjce i druge domaće životinje, što im je i donijelo ime.\n\nOvi jastrebovi su izuzetno snažni letači i koriste svoje oštre kandže i kljun kako bi uhvatili svoj plijen. Jastrebovi kokošinjci su i sposobni graditi velika gnijezda na visokim drvećima ili stupovima. Oni su teritorijalni i brane svoje gnijezdo od drugih ptica grabljivica. Jastrebovi kokošinjci su važan dio ekosustava jer pomažu kontrolirati populaciju manjih životinja.\n"));
        dataList.add(new ListItem("Sova Sneško", R.drawable.grabljivica10,"Sova Sneško\n\nSova Sneško, poznata i kao polarna sova, je predivna vrsta sove koja živi u hladnim arktičkim područjima. Ona je poznata po svom debelom bijelom perju koje joj pomaže da se dobro kamuflira u snježnom okruženju. Sova Sneško ima izuzetno dobar sluh i vid, što joj omogućava da loviti plijen čak i u uvjetima smanjene vidljivosti. Ove sove se hrane malim sisavcima, poput leminga i miševa, kao i pticama. Sova Sneško također ima sposobnost da okreće svoju glavu gotovo 270 stupnjeva kako bi bolje promatrala svoje okruženje.\n\nOve sove su prilagođene hladnim uvjetima i mogu izdržati ekstremno niske temperature. Sova Sneško je simbol snage, mudrosti i ljepote u mnogim kulturama. Sova Sneško ima izuzetno mekan i pahuljast perje koje joj pomaže da zadrži toplinu u hladnim uvjetima. Ove sove imaju vrlo snažne kandže koje koriste za hvatanje i držanje plijena.\n\nSova Sneško ima vrlo tihu letjelicu, gotovo nečujnu, što joj pomaže da se neprimjetno približi svojem plijenu. Ove sove su sjajni lovci i mogu uhvatiti plijen i do pola svoje težine.\n"));
    } else if ("Egzotične ribe".equals(category)) {
        dataList.add(new ListItem("Archer Fish ", R.drawable.ribaa1,"Archer riba\n\nArcher riba, poznata i kao riba strijelac, je vrsta ribe koja živi u slatkim i slanim vodama. Ove ribe su poznate po svojoj jedinstvenoj sposobnosti da izbacuju vodu iz svojih usta kako bi pogodile svoj plijen.\n\nArcher ribe se hrane insektima i drugim manjim organizmima koji se nalaze iznad površine vode. Da bi pogodile svoj plijen, archer ribe koriste precizan mlaz vode koji izbacuju iz svojih usta. Ove ribe imaju izuzetno dobar vid i mogu precizno ciljati svoj plijen čak i na udaljenostima do nekoliko metara. Archer ribe su vrlo brze i okretne u vodi, što im pomaže da se uspješno kreću i love plijen. Ove ribe su prilično male, obično dosežu duljinu od oko 10-15 centimetara.\n\nArcher ribe su vrlo prilagodljive i mogu se pronaći u različitim vrstama vodenih staništa, poput rijeka, jezera i močvara. Ove ribe su popularne u akvaristici zbog svoje jedinstvene sposobnosti i atraktivnog izgleda. Archer ribe su poreklom iz jugoistočne Azije, ali su se proširile i na druge dijelove svijeta. Ove ribe često skoče iz vode kako bi uhvatile insekte koji se nalaze iznad površine. Archer ribe mogu biti vrlo precizne u pogotku svojeg plijena, često pogađajući ga iz prve.\n"));
        dataList.add(new ListItem("Dragon Wrasse Arapaima ", R.drawable.ribaa2,"Dragon Wrasse Arapaima\n\nDragon wrasse riba je prepoznatljiva po svojoj šarenolikoj i živopisnoj boji. Ove ribe mogu narasti do dužine od 30 centimetara. Dragon wrasse ribe se često nalaze u tropskim vodama Indijskog i Tihog oceana. \n\nMužjaci i ženke dragon wrasse riba imaju različite boje i izgledaju vrlo različito. Ove ribe se hrane raznim vrstama hrane, uključujući rakove, školjke i male ribe. Dragon wrasse ribe su vrlo aktivne i pokretne, često se kreću po morskom dnu i istražuju svoje okruženje. Ove ribe također imaju snažne čeljusti koje koriste za drobljenje hrane. \n\nDragon wrasse ribe su poznate po svojoj sposobnosti čišćenja drugih riba, uklanjajući nametnike i parazite sa svojih tijela. Ove ribe mogu biti prilično teritorijalne i brane svoj prostor od drugih riba. Dragon wrasse ribe su popularne u akvaristici zbog svoje ljepote i zanimljivog ponašanja.\n\n"));
        dataList.add(new ListItem("Puffer fish ", R.drawable.ribaa3,"Puffer riba\n\nPuffer ribe su poznate po svom jedinstvenom izgledu i sposobnosti da se naduvaju. Ove ribe se nalaze u toplim morskim vodama širom svijeta. Puffer ribe imaju oštre zube i moćan ugriz, što ih čini sposobnima da se obrane od predatora. \n\nNeki pufferi mogu narasti do impresivne veličine, poput japanske puffer ribe koja može doseći dužinu od 90 cm. Puffer ribe se hrane raznim vrstama hrane, uključujući male ribe, rakove i školjke. Ove ribe imaju sposobnost da proizvode otrov, koji koriste kao obrambeni mehanizam protiv predatora. \n\nPuffer ribe su poznate po svom smiješnom izgledu i velikim očima. Neki ljudi smatraju puffer ribe delikatesom, ali važno je napomenuti da je konzumiranje puffer ribe rizično zbog prisutnosti otrova. Puffer ribe su popularne u akvaristici zbog svog zanimljivog izgleda i ponašanja. Ove ribe su fascinantne i privlače pažnju mnogih ljudi svojom jedinstvenom anatomijom i ponašanjem.\n"));
        dataList.add(new ListItem("Axolotl  ", R.drawable.ribaa4,"Axolotl\n\nAxolotl riba je jedinstvena vrsta vodenog stvorenja koja je poznata po svojoj sposobnosti regeneracije. Ove ribe su endemske vrste i prirodno se javljaju samo u jezeru Xochimilco u Meksiku. \n\nAxolotli su poznati po svojim vanjskim škrgama koje se nalaze na stranama glave. Ove ribe imaju sposobnost regeneracije izgubljenih dijelova tijela, uključujući udove, rep i čak dio srca i mozga. Axolotli imaju neobičan izgled s pljosnatim glavama, velikim očima i vanjskim škrgama. Ove ribe su prilagodljive i mogu živjeti u različitim uvjetima vode. \n\nAxolotli su poznati po svojoj dugovječnosti i mogu živjeti i do 15 godina. Ove ribe su mesožderi i hrane se raznim vrstama hrane, uključujući male ribe, insekte i rakove. Axolotli su popularni kućni ljubimci zbog svog jedinstvenog izgleda i relativno jednostavne njege. Ove ribe su važne za znanstvena istraživanja jer imaju sposobnost regeneracije kože, mišića i živčanog sustava. Axolotli su ugrožena vrsta i suočavaju se s prijetnjama poput gubitka staništa i onečišćenja vode. Ova fascinantna stvorenja su postala simbol obnove i regeneracije u mnogim kulturama diljem svijeta.\n"));
        dataList.add(new ListItem("Mbu Puffer ", R.drawable.ribaa5,"Mbu Puffer\n\nMbu puffer riba je vrsta slatkovodne ribe koja je poznata po svom nevjerovatnom izgledu i veličini. Ove ribe su endemske vrste i prirodno se javljaju u rijeci Kongo u središnjoj Africi. \n\nMbu puffer ribe su poznate po svojoj veličini i mogu narasti do impresivnih 60 centimetara. Imaju karakterističan izgled s velikim očima i natečenim tijelom koje im daje izgled puffera. Mbu puffer ribe su mesožderi i hrane se raznim vrstama hrane, uključujući rakove, školjke i male ribe. Ove ribe imaju moćne čeljusti i zube koji su prilagođeni za lomljenje tvrdih školjki. \n\nMbu puffer ribe su popularni kućni ljubimci među akvaristima zbog svog jedinstvenog izgleda. Ove ribe zahtijevaju veliki akvarij s dovoljno prostora za plivanje i skrovišta. Mbu puffer ribe su inteligentne i mogu razviti blisku vezu s vlasnicima. Važno je napomenuti da su mbu puffer ribe otrovne i da mogu proizvesti tetrodotoksin, pa je važno rukovati s njima s oprezom.\n" ));
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
