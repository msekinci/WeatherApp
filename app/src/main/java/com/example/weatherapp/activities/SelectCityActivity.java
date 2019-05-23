package com.example.weatherapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.adapters.CitiesAdapter;
import com.example.weatherapp.data.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectCityActivity extends AppCompatActivity {

    private RecyclerView citiesRV;
    private CitiesAdapter citiesAdapter;
    private DbHelper dbHelper;
    private ArrayList<HashMap<String, String>> all_cities_list;
    private ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SelectCityActivity.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

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
}
