package com.example.asm_mob104_name.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Trang_truyen_Adapter extends RecyclerView.Adapter<Trang_truyen_Adapter.Vieehoder> {

    List<String> stringList;
    Context context;
    SharedPreferences preferences;

    public Trang_truyen_Adapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public Trang_truyen_Adapter.Vieehoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trangtruyen, null);
        return new Vieehoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trang_truyen_Adapter.Vieehoder holder, int position) {
        Picasso.get().load(preferences.getString("LINKAPI", "") + stringList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class Vieehoder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Vieehoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iteam_trangtruyen_img);
        }
    }
}
