package com.example.lostfound2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {
    TextView Objname,Locname,DateV,NameV,ContactV,Quest;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Objname=findViewById(R.id.objname);
        Locname=findViewById(R.id.locname);
        DateV=findViewById(R.id.dateV);

        NameV=findViewById(R.id.nameV);
        ContactV=findViewById(R.id.contactV);
        Quest=findViewById(R.id.quest);

        Objname.setText(getIntent().getStringExtra("Itemname"));
        Locname.setText(getIntent().getStringExtra("Location"));
        DateV.setText(getIntent().getStringExtra("Date"));
        Quest.setText(getIntent().getStringExtra("question"));
        NameV.setText(getIntent().getStringExtra("usname"));
        ContactV.setText(getIntent().getStringExtra("contact"));
   }
}