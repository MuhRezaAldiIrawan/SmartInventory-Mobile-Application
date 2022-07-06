package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inventoryapp.HelperClass.OnboardingAdapter;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout slideindicator;

    OnboardingAdapter sliderapadter;
    TextView[] dots;
    Button btn_started;
    Animation animation;
    int currentPos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.onboardingslider);
        slideindicator = findViewById(R.id.dots);
        btn_started = findViewById(R.id.getstarted);

        //Call adapter
        sliderapadter = new OnboardingAdapter(this);

        viewPager.setAdapter(sliderapadter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void create(View view){startActivity(new Intent(this, Registerr.class));}

    public void skip(View view){
        startActivity(new Intent(this, Registerr.class));
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position){

        dots = new TextView[3];
        slideindicator.removeAllViews();

        for(int i=0; i< dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);

            slideindicator.addView(dots[i]);
        }

        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.secondary));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;
            if(position == 0){
                btn_started.setVisibility(View.INVISIBLE);
            }else if (position == 1){
                btn_started.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_animation);
                btn_started.setAnimation(animation);
                btn_started.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}