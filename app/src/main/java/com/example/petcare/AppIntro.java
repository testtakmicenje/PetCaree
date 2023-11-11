package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;

public class AppIntro extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("Prva Pomoć", "Naša Prva pomoć pruža vam temeljno znanje i savjete o hitnim medecinskim postupcima. Naučite kako reagovati u slučaju nesreće, ozljeda ili zdravstvenih problema. ", R.drawable.appintro2icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Fitnes i aktivnosti", "Naš intro za temu \"Fitnes i aktivnosti\" omogućit će vam da aktivirate i održavate vitalnost vašeg ljubimca na najbolji mogući način. Bez obzira na vrstu ljubimca kojeg imate, aplikacija će vam omogućiti da pratite njihove dnevne šetnje, trčanje i igre. ", R.drawable.appintro3icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Zanimljivosti", "Pronađite svakodnevnu dozu zanimljivih činjenica i čudesnih priča. Zadivite prijatelje novim znanjem i osvježite svoj um sa fascinantnim pričama koje ćete voljeti podijeliti sa drugima. ", R.drawable.appintro4icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Evidencija prehrane", "U našem uvodnom dijelu za \"Evidenciju prehrane\", možete jednostavno bilježiti obroke vašeg ljubimca, pratiti prehrambene navike i unos hranjivih materija.", R.drawable.appintro5icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));

        setIndicatorColor(getResources().getColor(R.color.roza), getResources().getColor(R.color.white));

        showStatusBar(true);

        setImmersiveMode();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

}