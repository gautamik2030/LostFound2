package com.example.lostfound2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {

    // Declare variables
    Button already_user, mSignupBtn;
    TextInputLayout mName, mUsername, mEmail, mPassword;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign__up);

        mName = (TextInputLayout) findViewById(R.id.name);
        mUsername = (TextInputLayout) findViewById(R.id.userName);
        mEmail = (TextInputLayout) findViewById(R.id.email);
        mPassword = (TextInputLayout) findViewById(R.id.Password);
        mSignupBtn = findViewById(R.id.signup_btn);
        already_user = findViewById(R.id.already_user);

        fAuth = FirebaseAuth.getInstance();


        //if user has an account and clicks on Signin, send user to Login activity
        already_user.setOnClickListener(view -> {
            Intent intent= new Intent(Sign_Up.this, LoginActivity.class);
            startActivity(intent);
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getEditText().getText().toString().trim();
                String username = mUsername.getEditText().getText().toString().trim();
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();


                if(name.equals(""))
                {
                    mName.getEditText().setError("Name is required.");
                    return;
                }

                if(username.equals(""))
                {
                    mUsername.getEditText().setError("Username is required.");
                    return;
                }

                if(email.equals(""))
                {
                    mEmail.getEditText().setError("Email is required.");
                    return;
                }

                if(password.equals(""))
                {
                    mPassword.getEditText().setError("Password is required.");
                    return;
                }

                if(password.length() < 6) {
                    mPassword.getEditText().setError("Password must be at least 6 characters.");
                    return;
                }

//                progressBar.setVisibility(View.VISIBLE);

                //register the user in Firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //event listener to know if signup is successful or not
                        if(task.isSuccessful()) {

                            //create user object and send to realtime database
                            User user = new User(name, username, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        //display successful message to user
                                        Toast.makeText(Sign_Up.this, "User created successfully", Toast.LENGTH_LONG).show();

                                        //user signed up so send user to dashboard activity
                                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                    }
                                    else {
                                        //not successful so display error message to user
                                        Toast.makeText(Sign_Up.this, "Error occurred! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else {
                            //not successful so display error message to user
                            Toast.makeText(Sign_Up.this, "Error occurred! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListView2();
            }
        });

    }

    public void openListView2() {
        Intent intent= new Intent(this, recActivity.class);
        startActivity(intent);
    }


}