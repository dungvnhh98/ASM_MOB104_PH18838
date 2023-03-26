package com.example.asm_mob104_name.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob104_name.Activity.MainActivity_ThongTinTruyen;
import com.example.asm_mob104_name.Mode.BinhLuan;
import com.example.asm_mob104_name.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class BinhLuan_adapter extends RecyclerView.Adapter< BinhLuan_adapter.ViewHolder> {

    List<BinhLuan> binhLuans;
    MainActivity_ThongTinTruyen activity4;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
    public BinhLuan_adapter(List<BinhLuan> binhLuans, MainActivity_ThongTinTruyen activity4) {
        this.binhLuans = binhLuans;
        this.activity4 = activity4;
    }

    @NonNull
    @Override
    public BinhLuan_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity4).inflate(R.layout.item_binh_luan,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuan_adapter.ViewHolder holder, int position) {
            BinhLuan binhLuan = binhLuans.get(position);

            holder.tv_time.setText(format.format(binhLuan.time));
            holder.tv_nd.setText(binhLuan.ND);
            holder.tv_name.setText(binhLuan.fullname);

    }

    @Override
    public int getItemCount() {
        return binhLuans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_time,tv_nd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.iteam_bl_user);
            tv_nd = itemView.findViewById(R.id.iteam_bl_nd);
            tv_time = itemView.findViewById(R.id.iteam_bl_time);

        }
    }
}
