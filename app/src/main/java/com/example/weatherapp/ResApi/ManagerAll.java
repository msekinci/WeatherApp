package com.example.weatherapp.ResApi;


import com.example.weatherapp.models.CityInformationModel;
import com.example.weatherapp.models.DailyForecastModel;
import com.example.weatherapp.models.LocationModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<List<CityInformationModel>> getCityInformation(String cityName)
    {
        Call<List<CityInformationModel>> x = getRestApi(1).getCityInformation(cityName);
        return  x ;
    }

    public Call<List<DailyForecastModel>> getDailyForecastModel(int id)
    {
        Call<List<DailyForecastModel>> x = getRestApi(1).getDailyForecastModel(id);
        return  x ;
    }

    public Call<LocationModel> getLocation(String latlng){
        Call<LocationModel> x = getRestApi(2).getLocation(latlng,"AIzaSyDbMAicvSDp7Jm4E-nfLd4UkvqHJjKqUzE");
        return x;
    }
}
