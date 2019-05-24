package com.example.weatherapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weatherapp.GPStracker;
import com.example.weatherapp.R;
import com.example.weatherapp.ResApi.ManagerAll;
import com.example.weatherapp.adapters.SwipeAdapter;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.fragments.HomeFragment;
import com.example.weatherapp.models.LocationModel;
import com.example.weatherapp.models.SuperClass;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private ViewPager home_viewPager;
    private SwipeAdapter swipeAdapter;
    private LinearLayout homeLayout, settingLayout;

    private DbHelper dbHelper;
    public static boolean gps_state;

    public String oldlatlng=null;


    @Override
    public void onBackPressed() {
        System.exit(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tanimla();
        click();
    }
    public void tanimla(){
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        floatingActionButton = findViewById(R.id.addCityButton);
        home_viewPager = findViewById(R.id.home_viewPager);
        home_viewPager.setOffscreenPageLimit(1);
        swipeAdapter = new SwipeAdapter(getSupportFragmentManager(),getApplicationContext());
        home_viewPager.setAdapter(swipeAdapter);
        home_viewPager.setCurrentItem(0);
    }

    public void click(){
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SelectCityActivity.class);
                startActivity(i);
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(i);
            }
        });
    }




}
