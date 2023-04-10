package com.example.asm_mob104_name.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm_mob104_name.Mode.ThongBao;
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

import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import io.socket.client.Socket;

public class MainActivity_Home extends AppCompatActivity {
    SharedPreferences preferences;

    TextView tv_name;

    ImageView img_thongbao;
    private ActivityHomeBinding binding;
    private Socket mSocket;

    List<ThongBao> list;

    SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tv_name = findViewById(R.id.home_fullname);
        img_thongbao = findViewById(R.id.img_thongbao);

        list = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        {
            try {
                mSocket = IO.socket(preferences.getString("LINKAPI", ""));
            } catch (URISyntaxException e) {
            }
        }

        tv_name.setText(preferences.getString("FULLNAME", "Loading..."));
        img_thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Home.this,MainActivity_ThongBao.class);
                intent.putExtra("thongbao", (Serializable) list);
                startActivity(intent);
            }
        });
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



    private Emitter.Listener onNewNotify = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity_Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        JSONArray a = new JSONArray(data.getString("data"));
                        for (int i = 0; i < a.length(); i++) {
                            JSONObject b = a.getJSONObject(i);
                            if(b.getString("username").equals(preferences.getString("USERNAME", ""))){
                                ThongBao thongBao = new ThongBao(b.getString("_id"), b.getString("username"), b.getString("idcomic"), b.getBoolean("check"), format.parse(b.getString("thoigian")), b.getString("_id"));
                                list.add(thongBao);
                            }
                        }
                    } catch (JSONException e) {
                        return;
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    };
}