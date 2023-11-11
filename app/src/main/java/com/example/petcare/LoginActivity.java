package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private boolean userLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }


        if (userLoggedIn) {

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onButtonLoginClick(View view) {

        Intent intent = new Intent(LoginActivity.this, Login1Activity.class);
        startActivity(intent);
    }

    public void onButtonRegisterClick(View view) {

        Intent intent = new Intent(LoginActivity.this, Login3Activity.class); // Promenite ActivityLogin1 sa pravim imenom aktivnosti
        startActivity(intent);
    }
}