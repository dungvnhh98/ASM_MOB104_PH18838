package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.WindowManager;


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
                editor.putString("LINKAPI", "https://a816-123-16-53-190.ap.ngrok.io/");
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
        },3000);
    }
}