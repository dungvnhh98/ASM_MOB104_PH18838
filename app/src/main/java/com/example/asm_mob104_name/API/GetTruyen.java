package com.example.asm_mob104_name.API;

import android.os.AsyncTask;
import android.util.Log;

import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.HomeFragment;
import com.example.asm_mob104_name.LogInFragment;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.Mode.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetTruyen extends AsyncTask<String, Integer, String> {
    HomeFragment context;

    public GetTruyen(HomeFragment context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            builder = new StringBuilder();
            String dong;
            while ((dong = reader.readLine()) != null) {
                builder.append(dong).append("\n");
            }
            reader.close();
            inputStream.close();
            connection.disconnect();
            Log.d("get truyện", "Backgroud tra về " + builder.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray arr = new JSONArray(s);
            List<Truyen> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject object = arr.getJSONObject(i);
                Truyen truyen = new Truyen();
                truyen.setIdTruyen(object.getString("id"));
                truyen.setTenTruyen(object.getString("name"));
                truyen.setMoTa(object.getString("mota"));
                truyen.setTacGia(object.getString("tacgia"));
                truyen.setNamXB(object.getInt("namxuatban"));
                truyen.setAnhBia(object.getString("anhbia"));
                truyen.setLuotXem(object.getInt("luotxem"));

                JSONArray a = new JSONArray(object.getString("noidung"));
                List<String> noidung = new ArrayList<>();
                for (int j = 0; j < a.length(); j++) {
                    noidung.add(a.get(j).toString());
                }
                truyen.setNoiDung(noidung.toArray(new String[0]));

                JSONArray b = new JSONArray(object.getString("luotthich"));
                List<String> luotthich = new ArrayList<>();
                for (int j = 0; j < b.length(); j++) {
                    luotthich.add(b.get(j).toString());
                }
                truyen.setLuotThich(luotthich.toArray(new String[0]));

                list.add(truyen);
            }

            Home_Adapter home_adapter = new Home_Adapter(list, context.getContext());
            context.gridView.setAdapter(home_adapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
