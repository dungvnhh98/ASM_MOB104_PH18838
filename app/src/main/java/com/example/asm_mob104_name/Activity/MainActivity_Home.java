package com.example.asm_mob104_name.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm_mob104_name.R;
import com.example.asm_mob104_name.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import io.socket.client.Socket;

public class MainActivity_Home extends AppCompatActivity {
    SharedPreferences preferences;

    TextView tv_name;

    ImageView img_thongbao;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tv_name = findViewById(R.id.home_fullname);
        img_thongbao = findViewById(R.id.img_thongbao);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        tv_name.setText(preferences.getString("FULLNAME", "Loading..."));
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_YT, R.id.navigation_CN)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);
        NavigationUI.setupWithNavController(binding.navView, navController);
        mSocket.on("thongbao", onNewNotify);
        mSocket.connect();
    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://3ac3-2405-4802-1d7a-e2d0-5150-6931-a96f-e5e6.ap.ngrok.io/");
        } catch (URISyntaxException e) {
        }
    }

    private Emitter.Listener onNewNotify = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity_Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        JSONArray a = new JSONArray(data.getString("data"));
                        Log.d("TAG", "cập nhật: " + a.length());
                    } catch (JSONException e) {
                        return;
                    }

                }
            });
        }
    };
}