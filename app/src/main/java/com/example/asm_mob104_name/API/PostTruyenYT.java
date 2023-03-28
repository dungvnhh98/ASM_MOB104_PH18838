package com.example.asm_mob104_name.API;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.Adapter.BinhLuan_adapter;
import com.example.asm_mob104_name.Adapter.Home_Adapter;
import com.example.asm_mob104_name.Adapter.YeuThich_Adapter;
import com.example.asm_mob104_name.Fragment.YeuThich_Fragment;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.Mode.Truyen;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostTruyenYT extends AsyncTask<String, Integer, String> {
    YeuThich_Fragment context;

    SharedPreferences preferences;

    public PostTruyenYT(YeuThich_Fragment context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getContext());
    }

    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("username", preferences.getString("USERNAME", ""));
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));


            bufferedWriter.append(posData.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

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
            Log.d("post yt", "Backgroud tra về " + builder.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject obj = new JSONObject(s);
            JSONArray arr = new JSONArray(obj.getString("a"));
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
                truyen.setNoiDung(noidung);

                JSONArray b = new JSONArray(object.getString("luotthich"));
                List<String> luotthich = new ArrayList<>();
                for (int j = 0; j < b.length(); j++) {
                    luotthich.add(b.get(j).toString());
                }
                truyen.setLuotThich(luotthich);

                list.add(truyen);
            }

            YeuThich_Adapter yeuThich_adapter = new YeuThich_Adapter(list, context.getContext());
            context.gridView.setAdapter(yeuThich_adapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
