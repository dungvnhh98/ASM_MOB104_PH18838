package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.example.asm_mob104_name.R;


public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editor.putString("LINKAPI", "https://3ac3-2405-4802-1d7a-e2d0-5150-6931-a96f-e5e6.ap.ngrok.io/");
                editor.commit();
                if(preferences.getString("USERNAME", "").equals("")){
                    Intent intent = new Intent(MainActivity.this, MainActivity_DKDN.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, MainActivity_Home.class);
                    startActivity(intent);
                }

                finish();

            }
        },100);
    }
}