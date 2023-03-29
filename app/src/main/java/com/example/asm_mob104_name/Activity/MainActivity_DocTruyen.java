package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.asm_mob104_name.Adapter.ViewPagerAdapter;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;


public class MainActivity_DocTruyen extends AppCompatActivity {
    ViewPager2 vp_trangtruyen;
    SharedPreferences preferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);

        vp_trangtruyen = findViewById(R.id.vp_doctruyen);

        Truyen truyen = (Truyen) getIntent().getSerializableExtra("truyen");
        Boolean doctiep = getIntent().getBooleanExtra("doctiep", false);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        vp_trangtruyen.setAdapter(new ViewPagerAdapter(truyen.noiDung, this, truyen.idTruyen));
        if(doctiep){
            vp_trangtruyen.setCurrentItem(preferences.getInt(truyen.idTruyen, 0)-1);
        }
    }
}