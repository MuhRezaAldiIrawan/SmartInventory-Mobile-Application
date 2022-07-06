package com.example.inventoryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        animatedBottomBar = findViewById(R.id.bottomnav);

        if (savedInstanceState == null){
            animatedBottomBar.selectTabById(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {

                Fragment fragment = null;
                switch (newTab.getId()){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.items:
                        fragment = new ItemsFragment();
                        break;

                    case R.id.inout:
                        fragment = new InOutFragment();
                        break;

                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                }
                if (fragment !=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                            .commit();
                }else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });

    }



}