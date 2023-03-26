package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.asm_mob104_name.Adapter.Trang_truyen_Adapter;
import com.example.asm_mob104_name.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity_DocTruyen extends AppCompatActivity {
    RecyclerView rcv_truyen;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);

        rcv_truyen = findViewById(R.id.trangtruyen_rcv);

        List<String> stringList = Arrays.asList(getIntent().getStringArrayExtra("noidung"));
        Trang_truyen_Adapter trang_truyen_adapter = new Trang_truyen_Adapter(stringList,getApplicationContext());


        LinearLayoutManager linearLayoutManagerTopSP = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_truyen.setLayoutManager(linearLayoutManagerTopSP);

        rcv_truyen.setAdapter(trang_truyen_adapter);

    }
}