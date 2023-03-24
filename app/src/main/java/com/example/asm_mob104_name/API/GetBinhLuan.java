package com.example.asm_mob104_name.API;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.Adapter.BinhLuan_adapter;
import com.example.asm_mob104_name.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Mode.BinhLuan;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetBinhLuan extends AsyncTask<String, Integer, String> {
    MainActivity_ThongTinTruyen context;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
    public GetBinhLuan(MainActivity_ThongTinTruyen context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("idcomic",context.truyen.getIdTruyen());
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
            Log.d("Getbl", "Backgroud tra về " + builder.toString());

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
            JSONArray array = new JSONArray(s);
            List<BinhLuan> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                list.add(new BinhLuan(object.getString("idcomic"), object.getString("username"), object.getString("noidung"), format.parse(object.getString("thoigian")),object.getString("fullname")));
            }
            BinhLuan_adapter adapter = new BinhLuan_adapter(list, context);


            LinearLayoutManager linearLayoutManagerTopSP = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            context.rcv_bl.setLayoutManager(linearLayoutManagerTopSP);
            context.rcv_bl.setAdapter(adapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
