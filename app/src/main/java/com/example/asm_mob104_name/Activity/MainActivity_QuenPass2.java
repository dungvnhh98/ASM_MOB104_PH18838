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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_QuenPass2 extends AppCompatActivity {
    String username;
    EditText edt_passmoi, edt_xnpass;
    TextInputLayout til_passmoi, til_xnpass;
    Button btn_doipass;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quen_pass2);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        username = getIntent().getStringExtra("username");

        edt_passmoi = findViewById(R.id.edt_passnew);
        edt_xnpass = findViewById(R.id.edt_xnpass);
        til_passmoi = findViewById(R.id.til_passnew);
        til_xnpass = findViewById(R.id.til_xnpass);
        btn_doipass = findViewById(R.id.btn_doipass);

        btn_doipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp =0;
                if(edt_passmoi.getText().toString().isEmpty()){
                    temp++;
                    til_passmoi.setError("Bạn chưa nhập mật khẩu mới");
                }else{
                    if(edt_passmoi.getText().toString().length()<6){
                        temp++;
                        til_passmoi.setError("Mật khẩu phải có ít nhất 6 ký tự");
                    }else{
                        til_passmoi.setError("");
                    }
                }

                if(edt_xnpass.getText().toString().isEmpty()){
                    temp++;
                    til_xnpass.setError("Bạn chưa xác nhận mật khẩu");
                }else{
                    if(edt_xnpass.getText().toString().equals(edt_passmoi.getText().toString())){
                        til_xnpass.setError("");
                    }else {
                        temp++;
                        til_xnpass.setError("Mật khẩu xác nhận chưa đúng");
                    }
                }
                if(temp==0){
                    postdoimatkhau();
                }
            }
        });
    }
    void postdoimatkhau() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            JSONObject a = new JSONObject();
            a.put("username", username);
            a.put("password", edt_passmoi.getText().toString());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, preferences.getString("LINKAPI", "") + "laylaipass", a, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "onResponse: " + response.toString());
                    try {
                        if(response.getBoolean("check")){
                            Toast.makeText(MainActivity_QuenPass2.this, "Đổi mật khẩu thành công.\nVui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity_QuenPass2.this, MainActivity_DKDN.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity_QuenPass2.this, "Đổi mật khẩu thất bại.\nVui lòng thử lại!", Toast.LENGTH_SHORT).show();
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