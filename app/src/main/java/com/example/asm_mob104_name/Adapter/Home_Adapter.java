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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Fragment.HomeFragment;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_Adapter extends BaseAdapter {
    static List<Truyen> truyens;
    HomeFragment context;
    TextView tv_tenTruyen, tv_luotXem, tv_luotthich;
    LinearLayout linearLayout;
    CheckBox checkBox;
    ImageView img_truyen;

    SharedPreferences preferences;

    public Home_Adapter(List<Truyen> truyens, HomeFragment context) {
        this.truyens = truyens;
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getContext());
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
            LayoutInflater inflater = (LayoutInflater) context.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_home, null);


        }

        tv_tenTruyen = view.findViewById(R.id.iteam_tenTruyen);
        tv_luotXem = view.findViewById(R.id.iteam_luotXem);
        tv_luotthich = view.findViewById(R.id.item_luotthich);
        img_truyen = view.findViewById(R.id.iteam_img);
        checkBox = view.findViewById(R.id.checkboxhome);
        linearLayout = view.findViewById(R.id.lnear1);

        Truyen truyen = truyens.get(i);

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyens.get(i).anhBia).into(img_truyen);
        tv_tenTruyen.setText(truyen.tenTruyen);
        tv_luotXem.setText("Lượt xem: " + truyen.luotXem);
        tv_luotthich.setText("Lượt thích: " + truyen.luotThich.size());
        if (truyen.luotThich.indexOf(preferences.getString("USERNAME", "")) < 0) {
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postluotthich(i);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getContext(), MainActivity_ThongTinTruyen.class);
                intent.putExtra("truyen", truyen);
                context.startActivity(intent);
            }
        });

        return view;
    }

    void postluotthich(int i) {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getContext());

        try {
            JSONObject a = new JSONObject();
            a.put("username", preferences.getString("USERNAME", ""));
            a.put("idcomic", truyens.get(i).getIdTruyen());
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
                                truyens.get(i).setLuotThich(lst);
                                notifyDataSetChanged();
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
