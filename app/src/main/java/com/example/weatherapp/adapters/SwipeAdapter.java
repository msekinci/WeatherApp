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

import static com.example.weatherapp.activities.LoadActivity.active_row_count;
public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
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
        return active_row_count;
    }
}

