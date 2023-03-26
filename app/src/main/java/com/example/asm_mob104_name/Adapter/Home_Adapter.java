package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm_mob104_name.API.PostLuotThich;
import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Adapter extends BaseAdapter {
    List<Truyen> truyens;
    Context context;
    TextView tv_tenTruyen, tv_luotXem, tv_luotthich;

    CheckBox checkBox;
    ImageView img_truyen;

    SharedPreferences preferences;

    public Home_Adapter(List<Truyen> truyens, Context context) {
        this.truyens = truyens;
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getCount() {
        return truyens.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_home, null);


        }

        tv_tenTruyen = view.findViewById(R.id.iteam_tenTruyen);
        tv_luotXem = view.findViewById(R.id.iteam_luotXem);
        tv_luotthich = view.findViewById(R.id.item_luotthich);
        img_truyen = view.findViewById(R.id.iteam_img);
        checkBox = view.findViewById(R.id.checkboxhome);
        checkBox.setChecked(false);

        Truyen truyen = truyens.get(i);

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyens.get(i).anhBia).into(img_truyen);
        tv_tenTruyen.setText(truyen.tenTruyen);
        tv_luotXem.setText("Lượt xem: "+truyen.luotXem);
        tv_luotthich.setText("Lượt thích: "+truyen.luotThich.length);
        for (int j = 0; j < truyen.luotThich.length; j++) {
            if(truyen.luotThich[j].equals(preferences.getString("USERNAME", ""))){
                checkBox.setChecked(true);
            }
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostLuotThich postLuotThich = new PostLuotThich(context, truyen.getIdTruyen(),tv_luotthich);
                postLuotThich.execute(preferences.getString("LINKAPI", "")+"postluotthich");

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_ThongTinTruyen.class);
                intent.putExtra("truyen", truyen);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
