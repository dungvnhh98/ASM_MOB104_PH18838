package com.example.asm_mob104_name.API;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.Activity.MainActivity_DKDN;
import com.example.asm_mob104_name.Activity.MainActivity_DoiMatKhau;
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

public class PostDoiMatKhau extends AsyncTask<String, Void, String> {
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    MainActivity_DoiMatKhau context;

    public PostDoiMatKhau(MainActivity_DoiMatKhau context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("username",preferences.getString("USERNAME", ""));
            posData.put("email",context.edt_email.getText().toString());
            posData.put("password",context.edt_passcu.getText().toString());
            posData.put("passmoi",context.edt_passmoi.getText().toString());

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
            Log.d("doimatkhau", "Backgroud tra về " + builder.toString());

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
            if (a.getBoolean("check")){
                editor.clear();
                editor.commit();
                context.startActivity(new Intent(context, MainActivity_DKDN.class));
                Toast.makeText(context, "Đổi mật khẩu thành công!\nVui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
            }else{
                context.til_email.setError("Sai mật khẩu hoặc email");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
