package com.example.weatherapp.ResApi;

public class BaseManager {

    // 1- for weather
    // 2- for location

    protected RestApi getRestApi(int i) {

        RestApiClient restApiClient;

        if (i==1){
            restApiClient = new RestApiClient(BaseUrl.WEATHER_URL);
            return restApiClient.getRestApi();
        }else if(i==2){
            restApiClient = new RestApiClient(BaseUrl.LOCATION_URL);
            return restApiClient.getRestApi();
        }else{
            return null;
        }
    }
}