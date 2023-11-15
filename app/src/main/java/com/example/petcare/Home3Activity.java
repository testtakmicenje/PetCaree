package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class Home3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3_activity);
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

        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview1_image);
                intent.putExtra("tekst", "\n" +
                        "Kupanje\n\n" +
                        "1. Priprema: Prije nego što krenete s kupanjem, pripremite sve što vam je potrebno. To uključuje toplu vodu, blagi šampon za životinje, peškir i četku ili češalj.\n" +
                        "\n" +
                        "2. Sigurnost: Osigurajte da je prostor u kojem ćete kupati životinju siguran i da nema opasnih predmeta ili skliskih površina. Također, provjerite da je temperatura vode ugodna za vašu životinju.\n" +
                        "\n" +
                        "3. Postupak: Nježno stavite životinju u kadu ili drugu posudu s toplom vodom. Koristite blagi šampon za životinje i temeljito ga nanesite na krzno, izbjegavajući područje oko očiju i ušiju. Nježno masirajte šampon u krzno kako biste uklonili prljavštinu i mirise. Isperite životinju temeljito čistom toplom vodom.\n" +
                        "\n" +
                        "4. Sušenje: Nakon kupanja, nježno obrišite životinju peškirom kako biste uklonili višak vlage. Ako vaša životinja to tolerira, možete koristiti fen za kosu na niskoj temperaturi ili hladnom zraku kako biste osušili krzno.\n" +
                        "\n" +
                        "5. Nagrada: Nakon kupanja, nagradite životinju za dobro ponašanje i strpljenje. To će im pomoći da povežu kupanje s pozitivnim iskustvom.\n");
                startActivity(intent);
            }
        });

        CardView cardView2 = findViewById(R.id.cardView2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview2_image);
                intent.putExtra("tekst", "\n" +
                        "Čiščenje zuba\n\n" +
                        "1. Četkanje zuba: Da biste održali zube svog ljubimca zdravima, preporučuje se redovito četkanje zuba. Koristite posebnu četkicu za zube za kućne ljubimce i specijalni zubni gel ili pastu koja je sigurna za njih. Počnite polako i postupno uvodite četkanje kako bi se vaš ljubimac navikao.\n" +
                        "\n" +
                        "2. Specijalne poslastice i igračke: Postoje posebne poslastice i igračke za životinje koje pomažu u održavanju zdravlja zuba. Ove poslastice i igračke mogu pomoći uklanjanju plaka i jačanju zubnog mesa.\n" +
                        "\n" +
                        "3. Redoviti veterinarski pregledi: Redoviti veterinarski pregledi su važni kako bi se održalo zdravlje zuba vašeg ljubimca. Veterinar će moći pregledati zube, ukloniti eventualni plak ili kamenac te pružiti dodatne savjete za njegu zuba.\n" +
                        "\n" +
                        "4. Pravilna prehrana: Kvalitetna prehrana bogata hranjivim tvarima također može imati pozitivan uticaj na zdravlje.\n");
                startActivity(intent);
            }
        });

        CardView cardView3 = findViewById(R.id.cardView3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview3_image);
                intent.putExtra("tekst", "\n" +
                        "Njega dlake\n\n" +
                        "1. Redovno četkanje: Redovno četkanje dlake pomaže u uklanjanju mrtve dlake i sprječavanju zapetljavanja. Ovo je posebno važno kod pasa i mačaka s dugom dlakom.\n" +
                        "\n" +
                        "2. Kupanje: Kupanje vašeg ljubimca treba biti redovito, ali ne prečesto. Koristite blagi šampon koji je namijenjen životinjama i dobro isperite dlaku kako ne bi ostao ostatak šampona.\n" +
                        "\n" +
                        "3.  Sušenje: Nakon kupanja, važno je temeljito osušiti dlaku. Možete koristiti peškir ili fen za kosu na niskoj temperaturi. Pazite da ne koristite previše topline kako ne biste oštetili kožu ili dlaku.\n" +
                        "\n" +
                        "4. Trimming (podrezivanje): Kod nekih pasmina pasa i mačaka, poput pudli ili terijera, potrebno je podrezivanje dlake kako bi se održao željeni izgled. Ovo se najbolje radi kod profesionalnog groomera.\n" +
                        "\n" +
                        "5. Hrana i dodaci prehrani: Pravilna prehrana bogata hranjivim tvarima također može pridonijeti zdravlju i sjaju dlake kod životinja.\n");
                startActivity(intent);
            }
        });
        CardView cardView4 = findViewById(R.id.cardView4);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview4_image);
                intent.putExtra("tekst", "\n" +
                        "Njega za nokte\n\n" +
                        "Njega za nokte životinja može biti važna kako bi se osiguralo da su njihovi nokti zdravi i održavani. \n" +
                        "\n" +
                        "Kod kućnih ljubimaca, kao što su psi i mačke, redovno podrezivanje noktiju može biti potrebno kako bi se spriječilo pucanje ili urastanje noktiju. \n" +
                        "\n" +

                        "Također, postoje posebne četke i alati za njegu noktiju kod životinja koje mogu pomoći u održavanju njihovih noktiju čistima i urednima. \n" +
                        "\n" +
                        "Važno je koristiti pravilne tehnike i alate kako biste izbjegli povredu životinje. \n");
                startActivity(intent);
            }
        });
        CardView cardView5 = findViewById(R.id.cardView5);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview5_image);
                intent.putExtra("tekst", "\n" +
                        "Čišćenje kreveta\n\n" +
                        "1. Redovno čišćenje: Važno je redovno čistiti krevet za životinje kako bi se uklonile dlake, prljavština i neugodni mirisi. Možete koristiti usisavač ili četku za uklanjanje dlaka, a zatim oprati krevet prema uputama proizvođača.\n" +
                        "\n" +
                        "2. Pranje: Većina kreveta za životinje može se prati u mašini za veš. Koristite blagi deterdžent i postavite na odgovarajući program. Nakon pranja, dobro osušite krevet prije nego što ga vratite kućnom ljubimcu.\n" +
                        "\n" +
                        "3.  Dodatne zaštite: Ako želite dodatno zaštititi krevet od dlaka, prljavštine ili vlage, možete koristiti zaštitne navlake ili plahte koje se lako peru.\n" +
                        "\n" +
                        "4. Zamjena: Ukoliko primijetite da je krevet za životinje oštećen ili više nije udoban, vrijeme je za zamjenu. Važno je osigurati da vaš ljubimac ima čist i udoban krevet za spavanje.\n" +
                        "\n" +
                        "Slijedeći ove savjete, osigurat ćete da krevet vašeg ljubimca bude čist, udoban i siguran za spavanje.\n");
                startActivity(intent);
            }
        });

        CardView cardView6 = findViewById(R.id.cardView6);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home3Activity.this, ActivityDetail.class);
                intent.putExtra("slika", R.drawable.cardview6_image);
                intent.putExtra("tekst", "\n" +
                        "Čišćenje akvarijuma\n\n" +
                        "1. Ispraznite akvarijum: Prvo, isključite sve električne uređaje i izvadite ribe, biljke i ukrase iz akvarijuma. Stavite ih u sigurnu posudu s vodom iz akvarijuma kako bi ostali zaštićeni.\n" +
                        "\n" +
                        "2. Uklonite prljavštinu: Pažljivo uklonite prljavštinu, alge i ostale naslage sa zidova i dna akvarijuma. Možete koristiti posebnu četku ili spužvu za čišćenje akvarijuma.\n" +
                        "\n" +
                        "3.  Promijenite vodu: Ispraznite svu vodu iz akvarijuma i isperite ga čistom vodom. Dodajte svježu vodu koja je iste temperature kao i prethodna voda u akvarijum.\n" +
                        "\n" +
                        "4. Vratite ribe i ukrase: Vratite ribe, biljke i ukrase u akvarijum nakon što ste ga očistili i napunili svježom vodom. Pazite da pažljivo vratite sve na svoje mjesto.\n" +
                        "\n" +
                        "5. Održavajte redovno čišćenje: Redovno čišćenje akvarijuma je važno kako bi se održala zdrava i čista vodena sredina za ribe. Planirajte čišćenje akvarijuma barem jednom mjesečno ili prema potrebi.\n");
                startActivity(intent);
            }
        });


    }
}