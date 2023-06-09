package com.example.asm_mob104_name.API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;

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

public class PostLuotXem extends AsyncTask<String, Integer, String> {
    Context context;
    TextView tv_luotxem;

    String idcomic;

    public PostLuotXem(Context context, TextView tv_luotxem, String idcomic) {
        this.context = context;
        this.tv_luotxem = tv_luotxem;
        this.idcomic = idcomic;
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
            Log.d("post", "Backgroud tra về " + builder.toString());

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
            if(tv_luotxem.getText().toString().contains("Lượt xem:")){
                tv_luotxem.setText("Lượt xem: " + a.getInt("luotxem"));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
