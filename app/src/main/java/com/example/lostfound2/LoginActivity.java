package com.example.lostfound2;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Declare variable
    Button createAccount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
        createAccount = findViewById(R.id.create_account);



        createAccount.setOnClickListener(view -> {
            Intent intent= new Intent(LoginActivity.this, Sign_Up.class);
            startActivity(intent);


        });




    }
}