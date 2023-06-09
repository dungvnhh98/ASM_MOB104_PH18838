package com.example.asm_mob104_name.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_mob104_name.Fragment.LogInFragment;
import com.example.asm_mob104_name.Fragment.RegisterFragment;

public class QL_DN_Adapter  extends FragmentStateAdapter {


    public QL_DN_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new LogInFragment();
        }else{
            return new RegisterFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
