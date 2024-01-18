package com.example.petcare.početna;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.petcare.forum.MainActivity;
import com.example.petcare.medecinskipodaci.MyMedicalInfo;
import com.example.petcare.prehrana.EvidencijaPrehrane;
import com.example.petcare.savjeti.Home5Activity;
import com.example.petcare.kviz.HomeqActivity;
import com.example.petcare.fitnesiaktivnosti.FitnesIAktivnosti;
import com.example.petcare.skeniranje.ScanFragment;
import com.example.petcare.troškovi.EvidencijaTroškova;
import com.example.petcare.R;
import com.example.petcare.egzoticniljubimci.ExoticPetsActivity;
import com.example.petcare.ljubimci.PetsActivity;
import com.example.petcare.mojiljubimci.MyPetsActivity;
import com.example.petcare.prvapomoc.Home2Activity;
import com.example.petcare.settings.SettingsActivity;
import com.example.petcare.vodičzanjegu.Home3Activity;
import com.example.petcare.weight.MyJobsAndMyProjects;
import com.example.petcare.zanimljivosti.Home4Activity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class HomeActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;


    private static final int MAX_BACK_PRESS_COUNT = 1;
    private int backPressCount = 0;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (backPressCount < MAX_BACK_PRESS_COUNT) {
            showToast("Pritisnite ponovo za izlazak.");
            backPressCount++;
            backPressedTime = System.currentTimeMillis();
        } else {
            exitApplication();
        }
    }

    private void exitApplication() {
        cancelToast(); // Cancel the toast when the application is exited
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private Toast currentToast;

    private void showToast(String message) {
        cancelToast(); // Cancel any existing toast before showing a new one
        currentToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        currentToast.show();
    }

    private void cancelToast() {
        if (currentToast != null) {
            currentToast.cancel();
            currentToast = null;
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        CardView petsCardView1 = findViewById(R.id.petsCardView1);
        CardView petsCardView2 = findViewById(R.id.petsCardView2);
        CardView exoticPetsCardView = findViewById(R.id.exoticPetsCardView);
        CardView careCardView1 = findViewById(R.id.careCardView1);
        CardView careCardView2 = findViewById(R.id.careCardView2);
        CardView careCardView4 = findViewById(R.id.careCardView4);
        CardView newCardView2 = findViewById(R.id.newCardView2);
        CardView newCardView3 = findViewById(R.id.newCardView3);
        CardView newCardView4 = findViewById(R.id.newCardView4);
        ImageView imageView = findViewById(R.id.image_view_1);

        drawerLayout = findViewById(R.id.drawer_layout_1);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView settingsIcon = findViewById(R.id.settingsIcon);

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otvorite novi ekran za postavke
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        petsCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PetsActivity.class);
                startActivity(intent);
            }
        });

        petsCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyPetsActivity.class);
                startActivity(intent);
            }
        });

        exoticPetsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ExoticPetsActivity.class);
                startActivity(intent);
            }
        });

        careCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyMedicalInfo.class);
                startActivity(intent);
            }
        });

        careCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        careCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EvidencijaPrehrane.class);
                startActivity(intent);
            }
        });

        newCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FitnesIAktivnosti.class);
                startActivity(intent);
            }
        });

        newCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyJobsAndMyProjects.class);
                startActivity(intent);
            }
        });

        newCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EvidencijaTroškova.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home1) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else if (id == R.id.home2) {
            Intent intent = new Intent(this, Home2Activity.class);
            startActivity(intent);
        } else if (id == R.id.home3) {
            Intent intent = new Intent(this, Home3Activity.class);
            startActivity(intent);
        } else if (id == R.id.home4) {
            Intent intent = new Intent(this, Home4Activity.class);
            startActivity(intent);
        } else if (id == R.id.home5) {
            Intent intent = new Intent(this, Home5Activity.class);
            startActivity(intent);
        } else if (id == R.id.homeq) {
            Intent intent = new Intent(this, HomeqActivity.class);
            startActivity(intent);
        }



        return true;
    }



}




