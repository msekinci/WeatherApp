package com.example.weatherapp.activities;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.weatherapp.GPStracker;
import com.example.weatherapp.R;
import com.example.weatherapp.ResApi.ManagerAll;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.LocationModel;

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
    public static ArrayList<HashMap<String, String>> active_city_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        active_city_list = new ArrayList<>();
        dbHelper = new DbHelper(LoadActivity.this);

        gpStracker = new GPStracker(LoadActivity.this);
        Location l = gpStracker.getLocation();


        if (l!=null){
            gps_state=true;
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            String latlng = String.valueOf(lat)+","+String.valueOf(lon);
            getLocation(latlng);

        }else{
            gps_state=false;
            int actiive_city_rows = dbHelper.getActiveRowCount();
            if (actiive_city_rows > 0){
                active_city_list=dbHelper.activeCities();
            }else if (actiive_city_rows == 0){
                dbHelper.activeCityWithName("1","İzmir");
                active_city_list = dbHelper.activeCities();
            }
            Intent i = new Intent(LoadActivity.this,MainActivity.class);
            startActivity(i);
        }
    }

    public void getLocation(String latlng){
        Call<LocationModel> req = ManagerAll.getInstance().getLocation(latlng);
        req.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {


                int active_row_count = dbHelper.getActiveRowCount();
                active_city_list = dbHelper.activeCities();
                Log.e("getLocation",String.valueOf(active_row_count));
                Log.e("getLocation",active_city_list.toString());
                Log.e("getLocation",response.body().getResults().get(0).getAddressComponents().get(4).getShortName());


                if (active_row_count==0){
                    Log.e("row == 0",active_city_list.toString());
                    dbHelper.activeCityWithName("1",response.body().getResults().get(0).getAddressComponents().get(4).getShortName());
                    Log.e("aktif edildi",response.body().getResults().get(0).getAddressComponents().get(4).getShortName());
                    active_city_list = dbHelper.activeCities();
                }else if(active_row_count>0){
                    ArrayList<HashMap<String, String>> temp_active_city = new ArrayList<>();
                    temp_active_city = dbHelper.activeCities();
                    Log.e("getLocation","büyüktür sıfıra girdi");
                    Log.e("getLocation3",temp_active_city.toString());

                    for (int i = 0; i<temp_active_city.size(); i++){
                        if (temp_active_city.get(i).get("il").equals(response.body().getResults().get(0).getAddressComponents().get(4).getLongName())){
                            active_city_list.clear();
                            dbHelper.activeCityWithName("1",response.body().getResults().get(0).getAddressComponents().get(4).getLongName());
                            active_city_list=dbHelper.activeCities();
                            for (int j = 0;j<active_city_list.size(); j++){
                                if (active_city_list.get(i).get("il").equals(response.body().getResults().get(0).getAddressComponents().get(4).getLongName())){
                                    Collections.swap(active_city_list,i,0);
                                }
                            }
                        }
                    }
                }
                Intent i = new Intent(LoadActivity.this,MainActivity.class);
                startActivity(i);


                /*if (isAdd){
                    dbHelper.activeCityWithName("1",response.body().getResults().get(0).getAddressComponents().get(4).getShortName());
                }else{
                    dbHelper.activeCityWithName("0",response.body().getResults().get(0).getAddressComponents().get(4).getShortName());
                }*/
            }
            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                Log.e("TAG:",t.toString());
            }
        });
    }
}
