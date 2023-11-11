package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login1Activity extends AppCompatActivity implements View.OnClickListener {



    private FirebaseAuth firebaseAuth;

    EditText emailEditText;

    EditText passwordEditText;


    Button loginButton;

    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        loginButton = (Button) findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(this);



        emailEditText = (EditText) findViewById(R.id.editTextEmail);

        passwordEditText = (EditText) findViewById(R.id.editTextPassword);

        loginButton = (Button) findViewById(R.id.buttonLogin);


        loginButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        createAuthStateListener();
        TextView textViewRegister = findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1Activity.this, Login3Activity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonLogin) {
            loginUser();
        } else {

        }
    }


    public void loginUser() {

        String email = emailEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, R.string.edit_text_email_hint, Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.edit_text_password_hint, Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent i = new Intent(Login1Activity.this, HomeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();

                        } else {

                            Toast.makeText(Login1Activity.this, R.string.provjera_autenticnosti_nije_uspjela_prijava_tekst, Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void createAuthStateListener() {
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(Login1Activity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

}