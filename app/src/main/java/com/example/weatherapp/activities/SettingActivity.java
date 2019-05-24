package com.example.weatherapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.weatherapp.R;

public class SettingActivity extends AppCompatActivity {

    Switch aSwitch;


    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingActivity.this, LoadActivity.class);
        startActivity(i);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        aSwitch = findViewById(R.id.aSwitch);
        aSwitch.setChecked(LoadActivity.gps_state);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        });
            //aSwitch.setChecked(MainActivity.gps_state);


    }
}
