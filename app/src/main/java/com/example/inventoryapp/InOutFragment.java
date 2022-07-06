package com.example.inventoryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventoryapp.HelperClass.SMRecyclerAdapter;
import com.example.inventoryapp.HelperClass.StockModel;

import java.util.ArrayList;


public class InOutFragment extends Fragment {

    ArrayList<StockModel> stockModels = new ArrayList<>();
    int[]stockmodelimages = {R.drawable.ic_download,R.drawable.ic_upload,R.drawable.ic_switch_vertical,
            R.drawable.ic_download,R.drawable.ic_upload, R.drawable.ic_upload};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_out, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.stock_recyclerview);

        setUpstockModels();

        SMRecyclerAdapter adapter = new SMRecyclerAdapter(this.getContext(), stockModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        return view;
    }

    private void setUpstockModels(){
        String[] stockinfo = getResources().getStringArray(R.array.infostock);
        String[] itemjml = getResources().getStringArray(R.array.jmlitem);
        String[] nameitem = getResources().getStringArray(R.array.itemname);


        for (int i = 0; i< itemjml.length; i++){
            stockModels.add(new StockModel(stockinfo[i], itemjml[i], nameitem[i],
                    stockmodelimages[i]));
        }
    }
}