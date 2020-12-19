package com.example.lostfound2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class recActivity extends AppCompatActivity {
    RecyclerView recView;
    //create myadapter object
    myadapter adapter;
    Button create, home;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rec);
        Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("objects"), model.class)
                        .build();

        adapter = new myadapter(options, getApplicationContext());
        recView.setAdapter(adapter);
        adapter.startListening();
        create = findViewById(R.id.button2);
        create.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(),CreatePost.class)));

        home = findViewById(R.id.home);
        home.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(),DashboardActivity.class)));

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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("objects").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), model.class)
                        .build();

        adapter = new myadapter(options, getApplicationContext());
        adapter.startListening();
        recView.setAdapter(adapter);


    }
}


