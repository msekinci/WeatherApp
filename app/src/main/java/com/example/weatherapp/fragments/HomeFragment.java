package com.example.weatherapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.ResApi.ManagerAll;
import com.example.weatherapp.activities.MainActivity;
import com.example.weatherapp.activities.SelectCityActivity;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.CityInformationModel;
import com.example.weatherapp.models.DailyForecastModel;
import com.example.weatherapp.models.LocationModel;
import com.example.weatherapp.models.SuperClass;
import com.example.weatherapp.models.TahminItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private ArrayList<HashMap<String, String>> active_city_list;
    ArrayList<HashMap<String, String>> gps_state_list;

    private ProgressDialog progressDialog;

    private View view;
    private TextView sehirText, dereceText, nemText, ruzgarhiziText,ruzgaryonuText;
    private DbHelper dbHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        sehirText = view.findViewById(R.id.sehirText);
        dereceText = view.findViewById(R.id.dereceText);
        nemText = view.findViewById(R.id.nemText);
        ruzgarhiziText = view.findViewById(R.id.ruzgarhiziText);
        ruzgaryonuText = view.findViewById(R.id.ruzgaryonuText);

        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");

        dbHelper = new DbHelper(getContext());
        active_city_list = dbHelper.activeCities();
        getCityInformation(active_city_list.get(pageNumber).get("il"));

        return view;
    }

    public void getCityInformation(String cityName) {
        Call<List<CityInformationModel>> request = ManagerAll.getInstance().getCityInformation(cityName);
        request.enqueue(new Callback<List<CityInformationModel>>() {
            @Override
            public void onResponse(Call<List<CityInformationModel>> call, Response<List<CityInformationModel>> response) {
                if (response.isSuccessful()) {
                    getDailyForecast(response.body().get(0).getSaatlikTahminIstNo());
                    sehirText.setText(response.body().get(0).getIl());
                }
            }

            @Override
            public void onFailure(Call<List<CityInformationModel>> call, Throwable t) {
                Log.e("HATA : ", t.getMessage());
            }
        });
    }

    public void getDailyForecast(int id) {
        final Call<List<DailyForecastModel>> request = ManagerAll.getInstance().getDailyForecastModel(id);
        request.enqueue(new Callback<List<DailyForecastModel>>() {
            @Override
            public void onResponse(Call<List<DailyForecastModel>> call, Response<List<DailyForecastModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() != 0){
                        List<TahminItem> tahminItems = response.body().get(0).getTahmin();
                        dereceText.setText(String.valueOf(tahminItems.get(response.body().size()).getSicaklik()));
                        nemText.setText(String.valueOf(tahminItems.get(response.body().size()).getNem()));
                        ruzgarhiziText.setText(String.valueOf(tahminItems.get(response.body().size()).getRuzgarHizi()));
                        ruzgaryonuText.setText(String.valueOf(tahminItems.get(response.body().size()).getRuzgarYonu()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DailyForecastModel>> call, Throwable t) {

            }
        });
    }









}
