package com.example.asm_mob104_name.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob104_name.Mode.Truyen;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetTrangTruyen {

    public void  getSringvolley(Context context, RecyclerView  recyclerView,String url){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(context, "loadok", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, "tải thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        List<String> stringList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            String a = new String("");
                            try {
                                JSONObject object = response.getJSONObject(i);
                                a = object.getString("noiDung");
                                stringList.add(a);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getTRangTruyen", "onErrorResponse: ");
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }
}
