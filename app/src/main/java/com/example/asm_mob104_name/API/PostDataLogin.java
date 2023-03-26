package com.example.asm_mob104_name.API;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.Fragment.LogInFragment;
import com.example.asm_mob104_name.Activity.MainActivity_Home;

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

public class PostDataLogin extends AsyncTask<String, Void, String> {
    SharedPreferences.Editor editor;
    LogInFragment context;

    public PostDataLogin(LogInFragment context) {
        this.context = context;
        editor = PreferenceManager.getDefaultSharedPreferences(context.getContext()).edit();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("username",context.edt_username.getText().toString());
            posData.put("password",context.edt_password.getText().toString());
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
            if (a.getBoolean("checklogin")){
                editor.putString("USERNAME", context.edt_username.getText().toString());
                editor.putString("FULLNAME", a.getString("fullname"));
                editor.commit();
                context.startActivity(new Intent(context.getContext(), MainActivity_Home.class));
                Toast.makeText(context.getContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                context.til_username.setError("Sai tên đăng nhập hoặc mật khẩu");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}
