package com.example.inventoryapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class HomeFragment extends Fragment {

    TextView TVName, TVday, TVdate;
    String username;
    SharedPreferences sp;
    ImageButton opencam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);



        Bundle extra = getActivity().getIntent().getExtras();
        opencam = view.findViewById(R.id.opencam);
        TVday = view.findViewById(R.id.TVday);
        TVdate = view.findViewById(R.id.Date_Infostock);
        TVName = view.findViewById(R.id.TVName);

        //GetUsername From Database
        sp = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        username = sp.getString("nama", null);
        TVName.setText(username);

//        if (extra != null) {
//
//            tvname = extra.getString("ETSU1");
//            TVName.setText(tvname);
//        }


        day();
        nowdate();

        opencam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_cam, 100);
            }
        });

        return view;
    }

    private void nowdate(){
        Calendar tgl = Calendar.getInstance();
        String curDate = DateFormat.getDateInstance().format(tgl.getTime());
        TVdate.setText(curDate);
    }

    private void day() {
        Calendar kalender = Calendar.getInstance();
        int jam = kalender.get(Calendar.HOUR_OF_DAY);

        if (jam >= 0 && jam < 12) {
            TVday.setText("Good Morning,");
        } else if (jam >= 12 && jam < 18) {
            TVday.setText("Good Afternoon,");
        } else if (jam >= 18 && jam < 24) {
            TVday.setText("Good Evening,");
        }
    }
}