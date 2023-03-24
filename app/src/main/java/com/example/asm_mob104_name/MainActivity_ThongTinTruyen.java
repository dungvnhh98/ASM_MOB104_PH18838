package com.example.asm_mob104_name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.API.GetBinhLuan;
import com.example.asm_mob104_name.API.PostBinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;
import com.squareup.picasso.Picasso;

public class MainActivity_ThongTinTruyen extends AppCompatActivity {
    Button btn_doc, btn_thich, btn_bl;
    public Truyen truyen;
    TextView tv_ten, tv_tacgia, tv_namxb, tv_luotthich, tv_views, tv_mota;

    public RecyclerView rcv_bl;
    public EditText edt_binhluan;
    ImageView img_anhbia;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_truyen);

        btn_doc = findViewById(R.id.truyen_btn_doc);
        img_anhbia = findViewById(R.id.img_anhbia);
        tv_ten = findViewById(R.id.truyen_ten);
        tv_mota = findViewById(R.id.truyen_MT);
        tv_luotthich = findViewById(R.id.truyen_LT);
        tv_namxb = findViewById(R.id.truyen_XB);
        tv_tacgia = findViewById(R.id.truyen_TG);
        tv_views = findViewById(R.id.truyen_views);
        edt_binhluan = findViewById(R.id.truyen_edt_cmt);
        btn_bl = findViewById(R.id.btn_guicmt);
        rcv_bl = findViewById(R.id.rcv_cmt);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        truyen = (Truyen) getIntent().getSerializableExtra("truyen");
        tv_ten.setText(truyen.getTenTruyen());
        tv_tacgia.setText("Tác giả: " + truyen.getTacGia());
        tv_views.setText("Lượt xem: " + truyen.getLuotXem());
        tv_luotthich.setText("Lượt thích: " + truyen.getLuotThich().length);
        tv_namxb.setText("Năm xuất bản: " + truyen.getNamXB());
        tv_mota.setText(truyen.getMoTa());

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyen.anhBia).into(img_anhbia);


        GetBinhLuan getBinhLuan = new GetBinhLuan(this);
        getBinhLuan.execute(preferences.getString("LINKAPI", "") + "getbinhluan");

        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_ThongTinTruyen.this, MainActivity_DocTruyen.class);
                intent.putExtra("noidung", truyen.getNoiDung());
                startActivity(intent);
            }
        });

        btn_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_binhluan.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity_ThongTinTruyen.this, "Bạn chưa bình luận gì!", Toast.LENGTH_SHORT).show();
                } else {
                    PostBinhLuan postBinhLuan = new PostBinhLuan(MainActivity_ThongTinTruyen.this);
                    postBinhLuan.execute(preferences.getString("LINKAPI", "") + "postbinhluan");
                }
            }
        });
    }
}