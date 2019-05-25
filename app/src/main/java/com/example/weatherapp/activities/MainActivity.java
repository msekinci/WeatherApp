package com.example.weatherapp.activities;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.weatherapp.R;
import com.example.weatherapp.adapters.SwipeAdapter;
import com.example.weatherapp.data.DbHelper;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private ViewPager home_viewPager;
    private SwipeAdapter swipeAdapter;
    private LinearLayout homeLayout, settingLayout;

    @Override
    public void onBackPressed() {
        System.exit(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        tanimla();
        click();
    }
    public void tanimla(){
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        floatingActionButton = findViewById(R.id.addCityButton);
        home_viewPager = findViewById(R.id.home_viewPager);
        home_viewPager.setOffscreenPageLimit(1);
        swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
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
