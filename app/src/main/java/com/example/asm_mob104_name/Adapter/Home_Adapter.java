package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob104_name.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Adapter extends BaseAdapter {
    List<Truyen> truyens;
    Context context;
    TextView tv_tenTruyen, tv_luotXem, tv_luotthich;
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

        Picasso.get().load(preferences.getString("LINKAPI", "") + truyens.get(i).anhBia).into(img_truyen);
        tv_tenTruyen.setText(truyens.get(i).tenTruyen);
        tv_luotXem.setText(truyens.get(i).luotXem + "");
        tv_luotthich.setText(""+truyens.get(i).luotThich.length);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_ThongTinTruyen.class);
                intent.putExtra("truyen", truyens.get(i));
                context.startActivity(intent);
            }
        });


        return view;
    }
}
