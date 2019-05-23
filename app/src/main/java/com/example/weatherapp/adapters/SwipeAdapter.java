package com.example.weatherapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.SuperClass;
import com.example.weatherapp.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;


public class SwipeAdapter extends FragmentStatePagerAdapter {

    private DbHelper dbHelper;
    private Context context;

    public SwipeAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        //Gösterilen şehrin listedeki sırasını gönderir homefragment tarafında liste hanig sıradaysa o sırayla kaydırarak görüntülenir
        Fragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position);
        homeFragment.setArguments(bundle);

        return homeFragment;
    }

    @Override
    public int getCount() {
        dbHelper = new DbHelper(context);
        int count = dbHelper.getActiveRowCount();
        return count;
    }
}

