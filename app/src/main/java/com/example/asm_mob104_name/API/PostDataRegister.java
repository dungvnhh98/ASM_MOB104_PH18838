package com.example.asm_mob104_name.API;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.Fragment.RegisterFragment;

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
import java.util.List;

public class PostDataRegister extends AsyncTask<String, Integer, String> {

    RegisterFragment context;
    List<User> userList;

    public PostDataRegister(RegisterFragment context) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    protected String doInBackground(String... strings) {
        String noiDung = "";
        StringBuilder builder;
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("username",context.edt_username.getText().toString());
            posData.put("email",context.edt_email.getText().toString());
            posData.put("password",context.edt_password.getText().toString());
            posData.put("fullname",context.edt_fullname.getText().toString());
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
            if(!a.getBoolean("checkusername")){
                context.til_username.setError("Tên tài khoản này đã được đăng ký");
            }else{
                if(!a.getBoolean("checkemail")){
                    context.til_email.setError("Email này đã được đăng ký");
                }else{
                    context.edt_email.setText("");
                    context.edt_username.setText("");
                    context.edt_fullname.setText("");
                    context.edt_password.setText("");
                    Toast.makeText(context.getContext(), "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
