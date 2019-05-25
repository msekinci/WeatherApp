package com.example.weatherapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.weatherapp.R;
import com.example.weatherapp.adapters.CitiesAdapter;
import com.example.weatherapp.data.DbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.example.weatherapp.activities.LoadActivity.active_city_list;
import static com.example.weatherapp.activities.LoadActivity.locationCity;

public class SelectCityActivity extends AppCompatActivity {

    private RecyclerView citiesRV;
    private CitiesAdapter citiesAdapter;
    private DbHelper dbHelper;
    private ArrayList<HashMap<String, String>> all_cities_list;

    private ImageView search;
    ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {

        //Şehir seçtikten sonra GPS durumuna göre konumu en ön sıraya çıkaracak

        if (LoadActivity.gps_state){
            while (!active_city_list.get(0).get("il").equals(locationCity)) {
                for (int i = 0; i < active_city_list.size(); i++) {
                    if (active_city_list.get(i).get("il").equals(locationCity)) {
                        Collections.swap(active_city_list, i, 0);
                    }
                }
            }
        }
        active_city_list = dbHelper.activeCities();
        Intent i = new Intent(SelectCityActivity.this,LoadActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        definitions();

    }

    public void definitions(){
        search = findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchAlert();
            }
        });
        citiesRV = findViewById(R.id.cityRV);
        RecyclerView.LayoutManager manager = new GridLayoutManager(SelectCityActivity.this,1);
        citiesRV.hasFixedSize();
        citiesRV.setLayoutManager(manager);
        progressDialog = new ProgressDialog(SelectCityActivity.this);
        progressDialog.setMessage("Kontrol edilirken bekleyiniz!");
        progressDialog.show();
        dbHelper = new DbHelper(getApplicationContext());
        all_cities_list = dbHelper.allCities();
        progressDialog.dismiss();
        citiesAdapter = new CitiesAdapter(SelectCityActivity.this,all_cities_list);
        citiesRV.setAdapter(citiesAdapter);
    }
    public void openSearchAlert(){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.search_layout,null);
        final EditText cityName = view.findViewById(R.id.cityNameAlert);
        ImageView searchAlert = view.findViewById(R.id.searchAlert);
        final AlertDialog.Builder alert = new AlertDialog.Builder(SelectCityActivity.this);
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat
        final AlertDialog alertDialog = alert.create();
        searchAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_cities_list = dbHelper.searchCities(cityName.getText().toString());
                citiesAdapter = new CitiesAdapter(SelectCityActivity.this,all_cities_list);
                citiesRV.setAdapter(citiesAdapter);
                alertDialog.cancel();
            }
        });
        alert.show();
    }
}
