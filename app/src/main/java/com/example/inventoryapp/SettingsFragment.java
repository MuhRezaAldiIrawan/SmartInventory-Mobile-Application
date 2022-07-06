package com.example.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingsFragment extends Fragment {

    ImageButton inf, toprofilepic;
    Button logout;
    TextView TVName;
    CircleImageView profilepic;
    String nama;
    SharedPreferences sp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        profilepic = view.findViewById(R.id.profilepic);
        TVName = view.findViewById(R.id.TVName);
        inf = view.findViewById(R.id.btn_next1);
        toprofilepic = view.findViewById(R.id.toprofilepic);
        logout = view.findViewById(R.id.logout1);
        sp = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        nama = sp.getString("nama", null);
        if (profilepic.equals(""))
            Picasso.get().load("http://tkjb2019.com/mobile/image/profile_default.png").into(profilepic);
        else
        {
            Picasso.get().load("http://tkjb2019.com/mobile/image/"+profilepic).into(profilepic);
        }
        TVName.setText(nama);



        toprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile;
                toProfile = new Intent(getActivity(), edit_profile.class);
                startActivity(toProfile);
            }
        });


        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInfo;
                toInfo = new Intent(getActivity(), Info.class);
                startActivity(toInfo);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Intent toLogin;
                toLogin = new Intent(getActivity(), Login.class);
                startActivity(toLogin);
            }
        });


        return view;
    }
}