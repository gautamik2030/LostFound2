package com.example.lostfound2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView recView;
    //create myadapter object
    myadapter adapter;
    FirebaseAuth fauth;
    String user;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        fauth = fauth.getInstance();
        FirebaseUser fuser = fauth.getCurrentUser();
        String uid = fuser.getUid();
        String user = fuser.getEmail();
        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("objects").orderByChild("username").startAt(user).endAt(user + "\uf8ff"), model.class)
                        .build();

        adapter = new myadapter(options);
        recView.setAdapter(adapter);
        adapter.startListening();
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    //search bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) item.getActionView();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //typing the entire string
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return true;
            }

            @Override
            //typing one character at a time
            public boolean onQueryTextChange(String s) {

                processsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //searching by key word
    private void processsearch(String s) {

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("objects").orderByChild("username").startAt(user).endAt(user + "\uf8ff").orderByChild("name").startAt(s).endAt(s+"\uff8ff"), model.class)
                        .build();

        adapter = new myadapter(options);
        adapter.startListening();
        recView.setAdapter(adapter);


    }



    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(DashboardActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}