package com.example.asm_mob104_name.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;

import java.util.ArrayList;
import java.util.List;


public class YeuThich_Fragment extends Fragment {

    GridView gridView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yeu_thich, container, false);

        gridView = view.findViewById(R.id.gv_YT);
        List<Truyen> truyens = new ArrayList<>();

        Truyen truyen = new Truyen("hahaha",null,40,new String[]{"1","2"});
        Truyen truyen1 = new Truyen("hahaha",null,40,null);

        Truyen truyen2 = new Truyen("hahaha",null,40,null);


        truyens.add(truyen);
        truyens.add(truyen1);
        truyens.add(truyen2);




        Home_Adapter home_adapter = new Home_Adapter(truyens,getContext());




        gridView.setAdapter(home_adapter);



        return view;
    }
}