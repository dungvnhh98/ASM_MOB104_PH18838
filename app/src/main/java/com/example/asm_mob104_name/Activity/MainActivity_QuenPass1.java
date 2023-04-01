package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob104_name.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_QuenPass1 extends AppCompatActivity {

    EditText edt_username, edt_mail;
    TextInputLayout til_username, til_mail;
    Button btn_next;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quen_pass1);

        edt_mail = findViewById(R.id.edt_mail);
        edt_username = findViewById(R.id.edt_acc);
        til_mail = findViewById(R.id.til_mail);
        til_username = findViewById(R.id.til_acc);
        btn_next = findViewById(R.id.btn_tieptuc);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = 0;
                if (edt_mail.getText().toString().isEmpty()) {
                    temp++;
                    til_mail.setError("Bạn chưa điền email");
                } else {
                    til_mail.setError("");
                }
                if (edt_username.getText().toString().isEmpty()) {
                    temp++;
                    til_username.setError("Bạn chưa điền tên đăng nhập");
                } else {
                    til_username.setError("");
                }
                if (temp == 0) {
                    postxacnhan();
                }
            }
        });
    }
    void postxacnhan() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            JSONObject a = new JSONObject();
            a.put("username", edt_username.getText().toString());
            a.put("email", edt_mail.getText().toString());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, preferences.getString("LINKAPI", "") + "xacnhanquenpass", a, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "onResponse: " + response.toString());
                    try {
                        if(response.getBoolean("check")){
                            Intent intent = new Intent(MainActivity_QuenPass1.this, MainActivity_QuenPass2.class);
                            intent.putExtra("username", edt_username.getText().toString());
                            startActivity(intent);
                        }else{
                            til_mail.setError("Chúng tôi không thể tìm thấy tên đăng nhập và email giống như bạn cung cấp.\nVui lòng kiểm tra và thử lại!");
                        }
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