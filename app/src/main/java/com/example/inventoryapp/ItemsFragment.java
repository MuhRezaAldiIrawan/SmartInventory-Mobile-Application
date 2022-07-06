package com.example.inventoryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ItemsFragment extends Fragment {

    ArrayList<String> array_nama, array_perusahaan, array_foto, array_stok;
    SwipeRefreshLayout srl_items;
    ListView itemsList;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        ImageButton additems = view.findViewById(R.id.additems);
        itemsList = view.findViewById(R.id.itemsLV);
        srl_items = view.findViewById(R.id.swipe_items);
        progressDialog = new ProgressDialog(getActivity());

        additems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddItems;
                toAddItems = new Intent(getActivity(), AddItems.class);
                startActivity(toAddItems);
            }
        });

        srl_items.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_items.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_items.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh();

        return view;
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },2000);
    }

    void initializeArray(){
        array_nama      = new ArrayList<String>();
        array_perusahaan = new ArrayList<String>();
        array_foto     = new ArrayList<String>();
        array_stok     = new ArrayList<String>();

        array_nama.clear();
        array_perusahaan.clear();
        array_foto.clear();
        array_stok.clear();

    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjb2019.com/mobile/api_kelompok_2/sm/getDatabarang.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_nama.add(jo.getString("name"));
                                    array_perusahaan.add(jo.getString("nim"));
                                    array_foto.add(jo.getString("address"));
                                    array_stok.add(jo.getString("hobby"));

                                }

//                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
//                                ItemsModel adapter = new ItemsModel(getActivity(), array_nama,array_foto,array_stok);
//                                //Set adapter to list
//                                itemsList.setAdapter(adapter);



                                //edit and delete
                                itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama.get(position));
//                                        Toast.makeText(MainActivity.this, array_name.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
//                                        Intent i = new Intent(getActivity(), edit_mahasiswa.class);
//                                        i.putExtra("nim",array_nama.get(position));
//                                        i.putExtra("name",array_perusahaan.get(position));
//                                        i.putExtra("address",array_foto.get(position));
//                                        i.putExtra("hobby",array_stok.get(position));
//                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(getActivity(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


}