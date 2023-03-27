package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob104_name.API.GetBinhLuan;
import com.example.asm_mob104_name.API.PostBinhLuan;
import com.example.asm_mob104_name.API.PostLuotXem;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_ThongTinTruyen extends AppCompatActivity {
    Button btn_doc, btn_bl;
    LinearLayout btn_thich;
    public Truyen truyen;
    public TextView tv_ten, tv_tacgia, tv_namxb, tv_luotthich, tv_views, tv_mota;

    public RecyclerView rcv_bl;
    public EditText edt_binhluan;
    ImageView img_anhbia, img_thich;

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
        btn_thich = findViewById(R.id.truyen_btn_yeuthich);
        img_thich = findViewById(R.id.img_thich);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        truyen = (Truyen) getIntent().getSerializableExtra("truyen");
        tv_ten.setText(truyen.getTenTruyen());
        tv_tacgia.setText("Tác giả: " + truyen.getTacGia());
        tv_views.setText("Lượt xem: " + truyen.getLuotXem());
        tv_luotthich.setText("Lượt thích: " + truyen.getLuotThich().size());
        tv_namxb.setText("Năm xuất bản: " + truyen.getNamXB());
        tv_mota.setText(truyen.getMoTa());
        if (truyen.getLuotThich().indexOf(preferences.getString("USERNAME", "")) < 0) {
            img_thich.setImageResource(R.drawable.checkboxfalse);
        } else {
            img_thich.setImageResource(R.drawable.checkboxtrue);
        }

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyen.anhBia).into(img_anhbia);


        GetBinhLuan getBinhLuan = new GetBinhLuan(this);
        getBinhLuan.execute(preferences.getString("LINKAPI", "") + "getbinhluan");

        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostLuotXem postLuotXem = new PostLuotXem(MainActivity_ThongTinTruyen.this);
                postLuotXem.execute(preferences.getString("LINKAPI", "") + "postluotxem");
                Intent intent = new Intent(MainActivity_ThongTinTruyen.this, MainActivity_DocTruyen.class);
                ArrayList<String> a = (ArrayList) truyen.getNoiDung();
                intent.putStringArrayListExtra("noidung", a);
                startActivity(intent);
            }
        });
        btn_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postluotthich();
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

    void postluotthich() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            JSONObject a = new JSONObject();
            a.put("username", preferences.getString("USERNAME", ""));
            a.put("idcomic", truyen.getIdTruyen());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, preferences.getString("LINKAPI", "") + "postluotthich", a, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "onResponse: " + response.toString());
                    try {
                        JSONArray a = response.getJSONArray("luotthich");
                        List<String> lst = new ArrayList<>();
                        for (int j = 0; j < a.length(); j++) {
                            lst.add(a.get(j).toString());
                        }
                        truyen.setLuotThich(lst);
                        tv_luotthich.setText("Lượt thích: " + truyen.getLuotThich().size());
                        if (truyen.getLuotThich().indexOf(preferences.getString("USERNAME", "")) < 0) {
                            img_thich.setImageResource(R.drawable.checkboxfalse);
                        } else {
                            img_thich.setImageResource(R.drawable.checkboxtrue);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.w("TAG", "onErrorResponse: ", error);
                }
            });
            requestQueue.add(request);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}