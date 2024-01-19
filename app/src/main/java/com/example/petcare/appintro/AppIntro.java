package com.example.petcare.appintro;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.petcare.login.LoginActivity;
import com.example.petcare.R;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;

public class AppIntro extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("Prepoznavanje simptoma", "Skenirajte svog ljubimaca putem kamere i brzo identifikujte moguće zdravstvene probleme. Pravovremeno prepoznavanje simptoma ključno je za pružanje najbolje moguće njege. ", R.drawable.appintro1icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Moji ljubimci", "Ovdje možete otkriti poseban segment posvećen vašim ljubimcima.  Bilo da ste vlasnik psa, mačke, ptice ili egzotičnog ljubimca, PetCare je tu kako bi vam olakšao brigu o vašem malenom prijatelju. Dodajte nove ljubimce, uređujte informacije i dijelite iskustva s drugim ljubiteljima životinja.  ", R.drawable.appintro2icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Medicinski podaci", "PetCare vam omogućava praćenje medicinskih podataka, uključujući dodavanje veterinarskih posjeta, terapija, bolesti... Imajte sve potrebne informacije na dohvat ruke kako biste osigurali dug život svog ljubimca.", R.drawable.appintro3icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Forum", "Dobrodošli na PetCare Forum - mjesto gdje ljubitelji životinja dijele iskustva, postavljaju pitanja i razmjenjuju savjete. Povežite se s drugim vlasnicima ljubimaca, saznajte korisne trikove i pridružite se zajednici koja dijeli vašu strast prema životinjama.", R.drawable.appintro4icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));
        addSlide(AppIntroFragment.newInstance("Evidencija prehrane", "U našem uvodnom dijelu za \"Evidenciju prehrane\", možete jednostavno pratiti obroke vašeg ljubimca, pomoću podsjetnika", R.drawable.appintro5icon, 0, ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.white), R.font.roboto_bold, R.font.roboto_light, R.drawable.pozadina5));


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