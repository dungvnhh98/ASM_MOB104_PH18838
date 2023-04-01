package com.example.asm_mob104_name.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.API.PostDataLogin;
import com.example.asm_mob104_name.Activity.MainActivity_QuenPass1;
import com.example.asm_mob104_name.R;
import com.google.android.material.textfield.TextInputLayout;


public class LogInFragment extends Fragment {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button btn_dn;

    TextView tv_quenpass;
    public EditText edt_username,edt_password;
    public TextInputLayout til_username,til_password;
    int check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_log_in, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        btn_dn = root.findViewById(R.id.btn_dn_login);
        edt_username = root.findViewById(R.id.edt_dn_username);
        edt_password = root.findViewById(R.id.edt_dn_password);
        til_username = root.findViewById(R.id.til_dn_name);
        til_password = root.findViewById(R.id.til_dn_pass);
        btn_dn = root.findViewById(R.id.btn_dn_login);
        tv_quenpass = root.findViewById(R.id.tv_dn_qmk);

        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
                if(Check()!= 0){
                }else {
                    PostDataLogin login = new PostDataLogin(LogInFragment.this);
                    login.execute(preferences.getString("LINKAPI", "") + "login");
                }
            }
        });
        tv_quenpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity_QuenPass1.class));
            }
        });

        return root;

    }
    public int Check(){
        check = 0;
        if(edt_username.getText().toString().isEmpty()){
            til_username.setError(" Không được để trống");
            check = 1;
        } else{
            til_username.setError("");
            check = 0;
        }
        if(edt_password.getText().toString().isEmpty()){
            til_password.setError(" Không được để trống");
            check = 1;
        } else{
            til_password.setError("");
            check = 0;
        }
        return check;
    }
    }
