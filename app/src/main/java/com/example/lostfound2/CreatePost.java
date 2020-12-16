package com.example.lostfound2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputEditText name,location,contact,question,date,username;
    //String username;
    Button submit;
    DatabaseReference ref;
    Objects1 obj;
    User user;
    FirebaseAuth fauth;
    private Spinner spinnerCategory;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpost);

        spinnerCategory = findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        spinnerCategory.setOnItemSelectedListener(this);



        name = (TextInputEditText) findViewById(R.id.name);
        location = (TextInputEditText) findViewById(R.id.location);
        contact = (TextInputEditText) findViewById(R.id.contact);
        question = (TextInputEditText) findViewById(R.id.question);
        date = (TextInputEditText) findViewById(R.id.answer);
        username = (TextInputEditText) findViewById(R.id.userName);

        submit = (Button) findViewById(R.id.submit);
        obj =new Objects1();
        ref = FirebaseDatabase.getInstance().getReference().child("objects");
        //user = new User();

        //Toast.makeText(CreatePost.this, username, Toast.LENGTH_LONG).show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser fuser = fauth.getCurrentUser();
                String uid = fuser.getUid();
                String user = fuser.getEmail();
                obj.setName(name.getText().toString().trim());
                obj.setLocation(location.getText().toString().trim());
                obj.setContact(contact.getText().toString().trim());
                obj.setQuestion(question.getText().toString().trim());
                obj.setCategory(spinnerCategory.toString().trim());
                obj.setDate(date.getText().toString().trim());
                obj.setUsername(username.getText().toString().trim());
                //String u = user.getUsername();
                //ref.push().setValue(obj);

                FirebaseDatabase.getInstance().getReference().child("objects").push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {
                            //display successful message to user
                            Toast.makeText(CreatePost.this,"Post Created Successfully.",Toast.LENGTH_LONG).show();

                            //user signed up so send user to dashboard activity
                            startActivity(new Intent(getApplicationContext(), recActivity.class));
                        }
                        else {
                            //not successful so display error message to user
                            Toast.makeText(CreatePost.this, "Error occurred! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
