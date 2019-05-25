package com.example.weatherapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.weatherapp.gps.GPStracker;
import com.example.weatherapp.R;
import com.example.weatherapp.ResApi.ManagerAll;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.LocationModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadActivity extends AppCompatActivity {

    private DbHelper dbHelper;//
    public static boolean gps_state;//
    GPStracker gpStracker;
    public static int active_row_count;
    public static String locationCity;
    public static ArrayList<HashMap<String, String>> active_city_list;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        active_city_list = new ArrayList<>();
        active_city_list.clear();

        dbHelper = new DbHelper(LoadActivity.this);
        gpStracker = new GPStracker(LoadActivity.this);
        Location l = gpStracker.getLocation();

        if (l != null) {
            gps_state = true;
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            String latlng = String.valueOf(lat) + "," + String.valueOf(lon);
            getLocation(latlng);

        } else {
            gps_state = false;
            if (locationCity != null) {
                dbHelper.activeCityWithName("0", locationCity);
            }
            active_row_count = dbHelper.getActiveRowCount();

            if (active_row_count > 0) {
                active_city_list = dbHelper.activeCities();

            } else {
                dbHelper.activeCityWithName("1", "İzmir");
                active_city_list = dbHelper.activeCities();
            }

            final ProgressDialog dialog = new ProgressDialog(LoadActivity.this);
            dialog.setMessage("Şehirleriniz yükleniyor!");
            dialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent i = new Intent(LoadActivity.this, MainActivity.class);
                    startActivity(i);
                    dialog.dismiss();

                }
            }, 1000);
        }


    }

    public void getLocation(String latlng) {
        Call<LocationModel> req = ManagerAll.getInstance().getLocation(latlng);
        req.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {

                active_row_count = dbHelper.getActiveRowCount();
                active_city_list = dbHelper.activeCities();
                if (response.body().getResults().get(0).getAddressComponents().get(4).getShortName() != null) {
                    locationCity = response.body().getResults().get(0).getAddressComponents().get(4).getShortName();
                }

                if (active_row_count == 0) {
                    dbHelper.activeCityWithName("1", locationCity);
                    active_city_list = dbHelper.activeCities();


                } else if (active_row_count > 0) {
                    ArrayList<HashMap<String, String>> temp_active_city = dbHelper.activeCities();
                    for (int i = 0; i < temp_active_city.size(); i++) {
                        if (temp_active_city.get(i).get("il").equals(locationCity)) {
                            for (int j = 0; j < active_city_list.size(); j++) {
                                if (active_city_list.get(j).get("il").equals(locationCity)) {
                                    Collections.swap(active_city_list, i, 0);
                                }
                            }
                        } else {
                            dbHelper.activeCityWithName("1", locationCity);
                        }
                    }
                    active_row_count = dbHelper.getActiveRowCount();
                }
                final ProgressDialog dialog = new ProgressDialog(LoadActivity.this);
                dialog.setMessage("Şehirleriniz yükleniyor!");
                dialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent i = new Intent(LoadActivity.this, MainActivity.class);
                        startActivity(i);
                        dialog.dismiss();

                    }
                }, 500);
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                Log.e("TAG:", t.toString());
            }
        });
    }
}
