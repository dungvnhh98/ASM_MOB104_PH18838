package com.example.asm_mob104_name.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.asm_mob104_name.Activity.MainActivity_DKDN;
import com.example.asm_mob104_name.Activity.MainActivity_Home;
import com.example.asm_mob104_name.R;


public class CaNhan_Fragment extends Fragment {
LinearLayout lnlo_dmk,lnlo_dx;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        lnlo_dmk = view.findViewById(R.id.lnlo_canhan_DMK);
        lnlo_dx = view.findViewById(R.id.lnlo_canhan_DX);

        lnlo_dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getActivity(), MainActivity_DKDN.class));
            }
        });

        lnlo_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity_Home.class));
            }
        });

        return view;

    }
}