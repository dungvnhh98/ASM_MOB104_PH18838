package com.example.asm_mob104_name;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob104_name.API.PostDataRegister;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment {
    SharedPreferences preferences;

    Button btn_dk;
    public EditText edt_username;
    public EditText edt_password;
    public EditText edt_email;
    public EditText edt_fullname;
    public TextInputLayout til_username, til_password, til_email, til_fullname;
    int check;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        btn_dk = root.findViewById(R.id.btn_dk_signup);
        edt_username = root.findViewById(R.id.edt_dk_username);
        edt_fullname = root.findViewById(R.id.edt_dk_fullname);
        edt_email = root.findViewById(R.id.edt_dk_email);
        edt_password = root.findViewById(R.id.edt_dk_password);
        til_username = root.findViewById(R.id.til_dk_name);
        til_password = root.findViewById(R.id.til_dk_pass);
        til_fullname = root.findViewById(R.id.til_dk_fullname);
        til_email = root.findViewById(R.id.til_dk_email);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check() != 0) {
                    Toast.makeText(getContext(), "Chưa Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                } else {
                    PostDataRegister postDataRegister = new PostDataRegister(RegisterFragment.this);
                    postDataRegister.execute(preferences.getString("LINKAPI", "") + "register");
                }
            }
        });


        return root;
    }

    public int Check() {
        check = 0;

        if (edt_username.getText().toString().isEmpty()) {
            til_username.setError(" Không được để trống");
            check++;
        } else {
            til_username.setError("");
        }

        if (edt_password.getText().toString().isEmpty()) {
            til_password.setError(" Không được để trống");
            check++;
        } else {
            til_password.setError("");
        }

        if (edt_email.getText().toString().isEmpty()) {
            til_email.setError(" Không được để trống");
            check++;
        } else {
            til_email.setError("");
        }

        if (edt_fullname.getText().toString().isEmpty()) {
            til_fullname.setError(" Không được để trống");
            check++;
        } else {
//            if (!validateLetters(edt_fullTen.getText().toString())) {
//                til_fullname.setError("Sai Định dạng");
//                check++;
//            } else {
            til_fullname.setError("");
//            }
        }


        return check;
    }

//    public static boolean validateLetters(String txt) {
//
//        String regx = "^[a-zA-Z\\s]+$";
//        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(txt);
//        return matcher.matches();
//
//    }
}
