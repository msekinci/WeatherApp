package com.example.weatherapp.ResApi;


import com.example.weatherapp.models.CityInformationModel;
import com.example.weatherapp.models.DailyForecastModel;
import com.example.weatherapp.models.LocationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    ///api/tahminler/saatlik


    @GET("api/merkezler")
    Call<List<CityInformationModel>> getCityInformation(@Query("il") String cityName);

    @GET("api/tahminler/saatlik")
    Call<List<DailyForecastModel>> getDailyForecastModel(@Query("istno") int cityId);

    @GET("api/geocode/json")
    Call<LocationModel> getLocation(@Query("latlng") String latlng, @Query("key") String key);


    /*//login işlemi
    @FormUrlEncoded
    @POST("/maypay/login.php")
    Call<LoginP> loginUser(@Field("emailAdress") String emailAdress, @Field("password") String password);*/



}