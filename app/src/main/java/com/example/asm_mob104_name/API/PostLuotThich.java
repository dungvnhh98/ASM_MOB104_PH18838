package com.example.asm_mob104_name.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;

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

public class PostLuotThich extends AsyncTask<String, Integer, String> {
    Context context;

    SharedPreferences preferences;
    String idcomic;

    TextView tv_luotthich;

    public PostLuotThich(Context context, String idcomic, TextView tv_luotthich) {
        this.context = context;
        this.idcomic = idcomic;
        this.tv_luotthich = tv_luotthich;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("idcomic", idcomic);
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
            Log.d("postluotthich", "Backgroud tra về " + builder.toString());

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
            JSONObject a = new JSONObject(s);
            JSONArray b = a.getJSONArray("luotthich");
            Log.d("TAG", "2: " + b.length());
            tv_luotthich.setText("Lượt thích: " + b.length());
            Log.d("TAG", "2: " + tv_luotthich.getText().toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
