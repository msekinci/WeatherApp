package com.example.weatherapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.weatherapp.GPStracker;
import com.example.weatherapp.R;
import com.example.weatherapp.data.DbHelper;

public class SettingActivity extends AppCompatActivity {

    Switch aSwitch;
    boolean changeStatus=false;


    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingActivity.this, LoadActivity.class);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (changeStatus){

            final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);
            dialog.setMessage("GPS durumunuzu kontrol ederken lütfen biraz bekleyiniz");
            dialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {


                    GPStracker gpStracker = new GPStracker(SettingActivity.this);
                    Location l = gpStracker.getLocation();
                    String message ;

                    if (l!=null){
                        message = "GPS Servisiniz Açık Durumdadır!";
                        aSwitch.setChecked(true);
                    }else{
                        message = "GPS Servisiniz Kapalı Durumdadır!";
                        aSwitch.setChecked(false);
                    }

                    dialog.dismiss();
                    Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }, 5000);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);
        dialog.setMessage("GPS durumunuzu kontrol ederken lütfen biraz bekleyiniz");
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                GPStracker gpStracker = new GPStracker(SettingActivity.this);
                Location l = gpStracker.getLocation();
                String message ;

                if (l!=null){
                    message = "GPS Servisiniz Açık Durumdadır!";
                    aSwitch.setChecked(true);
                    DbHelper dbHelper = new DbHelper(SettingActivity.this);
                    LoadActivity.active_row_count =dbHelper.getActiveRowCount();
                    LoadActivity.active_city_list = dbHelper.activeCities();
                }else{
                    message = "GPS Servisiniz Kapalı Durumdadır!";
                    aSwitch.setChecked(false);
                }

                dialog.dismiss();
                Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }, 5000);


        aSwitch = findViewById(R.id.aSwitch);
        aSwitch.setChecked(LoadActivity.gps_state);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()){
                    aSwitch.setChecked(false);
                    changeStatus=true;
                }else{
                    aSwitch.setChecked(true);
                    changeStatus=true;
                }
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        });
            //aSwitch.setChecked(MainActivity.gps_state);


    }
}
