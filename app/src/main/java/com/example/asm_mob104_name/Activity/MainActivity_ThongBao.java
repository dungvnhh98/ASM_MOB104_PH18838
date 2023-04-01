package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.asm_mob104_name.Mode.ThongBao;
import com.example.asm_mob104_name.R;

import java.util.List;

public class MainActivity_ThongBao extends AppCompatActivity {
    List<ThongBao> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_bao);
        list = (List<ThongBao>) getIntent().getSerializableExtra("thongbao");
        Toast.makeText(this, " "+list.size(), Toast.LENGTH_SHORT).show();
    }
}