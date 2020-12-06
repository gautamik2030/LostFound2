package com.example.lostfound2;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_Up extends AppCompatActivity {

    // Declare variables
    Button already_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign__up);

        already_user = findViewById(R.id.already_user);


        already_user.setOnClickListener(view -> {
            Intent intent= new Intent(Sign_Up.this, LoginActivity.class);
            startActivity(intent);
        });

    }


}