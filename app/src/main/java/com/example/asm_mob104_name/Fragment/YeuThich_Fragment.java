package com.example.asm_mob104_name.Fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.asm_mob104_name.API.PostTruyenYT;
import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;

import java.util.ArrayList;
import java.util.List;


public class YeuThich_Fragment extends Fragment {

    public GridView gridView;
    SharedPreferences preferences;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yeu_thich, container, false);

        gridView = view.findViewById(R.id.gv_YT);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        update();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    void update(){
        PostTruyenYT postTruyenYT = new PostTruyenYT(this);
        postTruyenYT.execute(preferences.getString("LINKAPI", "")+"posttruyen");
    }
}