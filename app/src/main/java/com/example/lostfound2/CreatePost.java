package com.example.lostfound2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePost extends AppCompatActivity{
    TextInputEditText name,location,contact,question,answer;
    String username;
    Button submit;
    DatabaseReference ref;
    Objects1 obj;
    User user;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpost);
        name = (TextInputEditText) findViewById(R.id.name_item);
        location = (TextInputEditText) findViewById(R.id.location);
        contact = (TextInputEditText) findViewById(R.id.contact);
        question = (TextInputEditText) findViewById(R.id.question);
        answer = (TextInputEditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.submit);
        obj =new Objects1();
        ref = FirebaseDatabase.getInstance().getReference().child("objects");
        user = new User();
        username = user.getUsername();
        Toast.makeText(CreatePost.this, username, Toast.LENGTH_LONG).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.setName(name.getText().toString().trim());
                obj.setLocation(location.getText().toString().trim());
                obj.setContact(contact.getText().toString().trim());
                obj.setQuestion(question.getText().toString().trim());
                obj.setAnswer(answer.getText().toString().trim());
                //String u = user.getUsername();
                //ref.push().setValue(obj);

                FirebaseDatabase.getInstance().getReference("objects")
                        .child(username)
                        .setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
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
}
