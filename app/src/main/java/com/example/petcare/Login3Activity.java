package com.example.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
public class Login3Activity extends AppCompatActivity {

    private Toolbar mTopToolbar;

    EditText nameAndSurnameEditText, usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    TextView termsOfUseTextView, privacyPolicyTextView;

    Button signUpButton;

    private FirebaseAuth firebaseAuth;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        firebaseAuth = FirebaseAuth.getInstance();

        usernameEditText = (EditText) findViewById(R.id.etUsername);

        nameAndSurnameEditText = (EditText) findViewById(R.id.etFullName);

        emailEditText = (EditText) findViewById(R.id.etEmail);

        passwordEditText = (EditText) findViewById(R.id.etPassword);

        confirmPasswordEditText = (EditText) findViewById(R.id.etRepeatPassword);

        signUpButton = (Button) findViewById(R.id.btnRegister);
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login3Activity.this, Login1Activity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String name = nameAndSurnameEditText.getText().toString().trim();
                String emaill = emailEditText.getText().toString().trim();
                String pass = passwordEditText.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {

                    emailEditText.setFocusable(true);

                } else if (pass.length() < 6) {

                    passwordEditText.setFocusable(true);

                } else {

                    registerUser(username, name, emaill, pass);

                }
            }
        });
    }


    public void registerUser(final String username, final String name, String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login3Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userID = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("id", userID);
                            map.put("username", username.toLowerCase());
                            map.put("fullname", name);
                            map.put("imageurl", "https://i.ibb.co/Yty5y07/userprofilepictureplaceholder.png");
                            map.put("bio", "");

                            reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(Login3Activity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        Button prijavitesedugme = (Button) findViewById(R.id.btnRegister);

        prijavitesedugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login3Activity.this, HomeActivity.class));
                //customType(Register.this, "bottom-to-up");
            }
        });

    }


    }

