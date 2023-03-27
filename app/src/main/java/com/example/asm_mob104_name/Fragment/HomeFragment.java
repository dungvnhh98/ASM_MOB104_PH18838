package com.example.asm_mob104_name.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.asm_mob104_name.API.GetTruyen;
import com.example.asm_mob104_name.Mode.Truyen;
import com.example.asm_mob104_name.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    public GridView gridView;
    ImageSlider imageSlider;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        gridView = view.findViewById(R.id.gv_home);
        imageSlider = view.findViewById(R.id.home_image);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img_2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_6, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        updatetruyen();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updatetruyen();
    }

    public void updatetruyen(){
        GetTruyen getTruyen = new GetTruyen(HomeFragment.this);
        getTruyen.execute(preferences.getString("LINKAPI", "") + "gettruyen");
    }
}