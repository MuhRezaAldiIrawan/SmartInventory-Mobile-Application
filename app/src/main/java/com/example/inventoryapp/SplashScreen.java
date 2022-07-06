package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    Animation topAnim, bottomAnim;
    ImageView imageview;
    TextView appname;
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageview = findViewById(R.id.box);
        appname = findViewById(R.id.textView);

        imageview.setAnimation(topAnim);
        appname.setAnimation(bottomAnim);


      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
              boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

              if(isFirstTime){

                  SharedPreferences.Editor editor = onBoardingScreen.edit();
                  editor.putBoolean("firstTime", false);
                  editor.commit();
                  Intent aftersplash = new Intent(SplashScreen.this,OnBoarding.class);
                  startActivity(aftersplash);
              } else{
                  Intent aftersplash = new Intent(SplashScreen.this,Registerr.class);
                  startActivity(aftersplash);
              }
              finish();
          }
      },SPLASH_SCREEN);

    }
}