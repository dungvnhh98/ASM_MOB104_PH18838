package com.example.asm_mob104_name.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asm_mob104_name.API.PostDoiMatKhau;
import com.example.asm_mob104_name.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity_DoiMatKhau extends AppCompatActivity {

    public EditText edt_passcu, edt_passmoi, edt_passxn, edt_email;
    public TextInputLayout til_passcu, til_passmoi, til_passxn, til_email;
    Button btn_doi;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doi_mat_khau);

        edt_email = findViewById(R.id.edt_email);
        edt_passcu = findViewById(R.id.edt_passcu);
        edt_passmoi = findViewById(R.id.edt_passmoi);
        edt_passxn = findViewById(R.id.edt_passxacnhan);
        til_email = findViewById(R.id.til_email);
        til_passcu = findViewById(R.id.til_passcu);
        til_passmoi = findViewById(R.id.til_passmoi);
        til_passxn = findViewById(R.id.til_passxacnhan);
        btn_doi = findViewById(R.id.btn_doimatkhau);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        btn_doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    PostDoiMatKhau postDoiMatKhau = new PostDoiMatKhau(MainActivity_DoiMatKhau.this);
                    postDoiMatKhau.execute(preferences.getString("LINKAPI", "")+"doipass");
                }
            }
        });
    }

    public boolean check() {
        int temp = 0;
        if(edt_email.getText().toString().isEmpty()){
            temp++;
            til_email.setError("Thiếu email xác nhận");
        }else{
            til_email.setError("");
        }

        if(edt_passcu.getText().toString().isEmpty()){
            temp++;
            til_passcu.setError("Thiếu mật khẩu");
        }else{
            til_passcu.setError("");
        }

        if(edt_passmoi.getText().toString().isEmpty()){
            temp++;
            til_passmoi.setError("Thiếu mật khẩu mới");
        }else{
            til_passmoi.setError("");
        }

        if(edt_passxn.getText().toString().isEmpty()){
            temp++;
            til_passxn.setError("Thiếu mật khẩu xác nhận");
        }else{
            til_passxn.setError("");
        }

        if(edt_passmoi.getText().toString().length()<6){
            til_passmoi.setError("Mật khẩu phải trên 6 kí tự");
            temp++;
        }else{
            til_passmoi.setError("");
        }

        if(edt_passmoi.getText().toString().equals(edt_passxn.getText().toString())){
            til_passxn.setError("");
        }else{
            til_passxn.setError("Mật khẩu xác nhận không khớp với mật khẩu mới");
            temp++;
        }
        if(temp==0){
            return true;
        }else{
            return false;
        }
    }
}