package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob104_name.API.PostLuotXem;
import com.example.asm_mob104_name.Activity.MainActivity_DocTruyen;
import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YeuThich_Adapter extends BaseAdapter {
    List<Truyen> truyens;
    Context context;
    TextView tv_tenTruyen;
    ImageView img_truyen;

    Button btn_doctiep, btn_doctudau;

    SharedPreferences preferences;

    public YeuThich_Adapter(List<Truyen> truyens, Context context) {
        this.truyens = truyens;
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getCount() {
        return truyens.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_yt, null);
        }

        tv_tenTruyen = view.findViewById(R.id.itemyt_name);
        img_truyen = view.findViewById(R.id.itemyt_img);
        btn_doctiep = view.findViewById(R.id.btn_doctiep);
        btn_doctudau = view.findViewById(R.id.btn_doctudau);

        Truyen truyen = truyens.get(i);

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyens.get(i).anhBia).into(img_truyen);
        tv_tenTruyen.setText(truyen.tenTruyen);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_ThongTinTruyen.class);
                intent.putExtra("truyen", truyen);
                context.startActivity(intent);
            }
        });

        btn_doctudau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostLuotXem postLuotXem = new PostLuotXem(context, tv_tenTruyen, truyen.getIdTruyen());
                postLuotXem.execute(preferences.getString("LINKAPI", "") + "postluotxem");
                Intent intent = new Intent(context, MainActivity_DocTruyen.class);
                intent.putExtra("truyen", truyen);
                context.startActivity(intent);
            }
        });
        btn_doctiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_DocTruyen.class);
                intent.putExtra("truyen", truyen);
                intent.putExtra("doctiep", true);
                context.startActivity(intent);
            }
        });
        return view;
    }


}
