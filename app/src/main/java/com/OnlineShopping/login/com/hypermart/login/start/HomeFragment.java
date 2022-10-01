package com.OnlineShopping.login.com.hypermart.login.start;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.OnlineShopping.login.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

    View view;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.gihan_activity_home, container, false);
        img = (ImageView) view.findViewById(R.id.fruits);
        img.setOnClickListener(this);

        return view;

    }

    public void onClick(View view) {

        String strText;

        SharedPreferences sp1 = this.getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        strText = sp1.getString("CurrentUser", null);


    }

}
