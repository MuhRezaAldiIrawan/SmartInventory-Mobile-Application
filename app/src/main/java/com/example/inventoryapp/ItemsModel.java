package com.example.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.inventoryapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ItemsModel extends ArrayAdapter<String> {
    //Declarasi variable
    private final Activity context;
    private ArrayList<String> vNama;
//    private ArrayList<String> vPerusahaan;
    private ArrayList<String> vFoto;
    private ArrayList<String> vStok;

    public ItemsModel(Activity context, ArrayList<String> Nama, ArrayList<String> Perusahaan,
                        ArrayList<String> Foto,ArrayList<String> Hobby, ArrayList<String> Stok)
    {
        super(context, R.layout.recycle_view_items,Nama);
        this.context    = context;
        this.vNama      = Nama;
//        this.vPerusahaan = Perusahaan;
        this.vFoto   = Foto;
        this.vStok     = Stok;
    }




    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View View= inflater.inflate(R.layout.recycle_view_items, null, true);

        //Declarasi komponen
        TextView nama       = View.findViewById(R.id.TVnamabarang);
        TextView stok        =  View.findViewById(R.id.TVjmlstock);
        ImageView photo     = View.findViewById(R.id.IVitem);


        //Set Parameter Value sesuai widget textview
        nama.setText(vNama.get(position));
        stok.setText(vStok.get(position));
        if (vFoto.get(position).equals(""))
        {
            Picasso.get().load("https://tkjb2019.com/mobile/image/profile_default.png").into(photo);
        }
        else
        {
            Picasso.get().load("https://tkjb2019.com/mobile/image/"+vFoto.get(position)).into(photo);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        View.startAnimation(animation);

        return View;
    }


}
