package com.example.lostfound2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation top_anim, bottom_anim;
    ImageView LogoImage;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations
        top_anim= AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottom_anim= AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //Hooks
        LogoImage = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);

        LogoImage.setAnimation(top_anim);
        text.setAnimation(bottom_anim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            Pair[] pairs = new Pair[2];
            pairs[0]= new Pair<View, String>(LogoImage,"logo_image");
            pairs[1]= new Pair<View, String>(text,"logo_text");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        }, SPLASH_SCREEN);

    }
}