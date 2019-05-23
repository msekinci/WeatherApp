package com.example.weatherapp.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.GPStracker;
import com.example.weatherapp.R;
import com.example.weatherapp.adapters.CitiesAdapter;
import com.example.weatherapp.data.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {

    Switch aSwitch;
    DbHelper dbHelper;
    ArrayList<HashMap<String, String>> gps_state_list;


    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        aSwitch = findViewById(R.id.aSwitch);

        dbHelper = new DbHelper(SettingActivity.this);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                    /*if (isChecked){
                        GPStracker g = new GPStracker(getApplicationContext());
                        Location l = g.getLocation();
                        if (l!=null){
                            double lat = l.getLatitude();
                            double lon = l.getLongitude();
                            Toast.makeText(SettingActivity.this, "LAT : "+lat+" LON : "+lon, Toast.LENGTH_SHORT).show();
                            dbHelper.activeGPS("1",String.valueOf(lat),String.valueOf(lon),"1");
                        }else{
                            aSwitch.setChecked(false);
                            dbHelper.activeGPS("0","0","0","1");
                        }
                    }else{

                    }*/
            }
        });
    }
}
