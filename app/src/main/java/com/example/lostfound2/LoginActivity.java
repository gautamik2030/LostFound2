package com.example.lostfound2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    // Declare variable
    Button createAccount, mSigninBtn;
    TextInputLayout mEmail, mPassword;
    FirebaseAuth fAuth;
    User user;
    TextInputEditText mail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
        mEmail = (TextInputLayout) findViewById(R.id.email);
        mPassword = (TextInputLayout) findViewById(R.id.password);
        mSigninBtn = findViewById(R.id.signin_page);
        createAccount = findViewById(R.id.create_account);
        mail = findViewById(R.id.mail1);

        fAuth = FirebaseAuth.getInstance();


        //if it is a returning user, no need to signin every time, send user to main activity
        if(fAuth.getCurrentUser() != null) {
            //user.setEmail(this.mEmail);
            startActivity(new Intent(getApplicationContext(), recActivity.class));
            finish();
        }


        //if user doesn't have an account and clicks on Create Account, send user to SignUp activity
        createAccount.setOnClickListener(view -> {
            Intent intent= new Intent(LoginActivity.this, Sign_Up.class);
            startActivity(intent);
        });


        mSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();
                String m = mail.toString();
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


                //authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // event listener to know if signin was successful or not
                        if(task.isSuccessful()) {
                            //user.setEmail(m);
                            //display successful message to user
                            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_LONG).show();
                            //user signed in so send user to dashboard activity
                            startActivity(new Intent(getApplicationContext(), recActivity.class));
                        }

                        else {
                            //not successful so display error message to user
                            Toast.makeText(LoginActivity.this, "Error occurred! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

//                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });



    }
}