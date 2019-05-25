package com.example.weatherapp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.ResApi.ManagerAll;
import com.example.weatherapp.activities.LoadActivity;
import com.example.weatherapp.activities.NoConnectionActivity;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.CityInformationModel;
import com.example.weatherapp.models.DailyForecastModel;
import com.example.weatherapp.models.TahminItem;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private View view;
    PieChart pieChart;
    private TextView nemText, ruzgarhiziText, ruzgaryonuText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Ekranda gösterilecek şehirin itemnosu
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");

        getCityInformation(LoadActivity.active_city_list.get(pageNumber).get("il"));

        nemText = view.findViewById(R.id.nemText);
        ruzgarhiziText = view.findViewById(R.id.ruzgarhiziText);
        ruzgaryonuText = view.findViewById(R.id.ruzgaryonuText);

        return view;
    }
    public void getCityInformation(final String cityName) {
        Call<List<CityInformationModel>> request = ManagerAll.getInstance().getCityInformation(cityName);
        request.enqueue(new Callback<List<CityInformationModel>>() {
            @Override
            public void onResponse(Call<List<CityInformationModel>> call, Response<List<CityInformationModel>> response) {
                if (response.isSuccessful()) {
                    getDailyForecast(response.body().get(0).getSaatlikTahminIstNo(),response.body().get(0).getIl());
                }
            }
            @Override
            public void onFailure(Call<List<CityInformationModel>> call, Throwable t) {
                Log.e("TAG : ", t.getMessage());
                Intent i = new Intent(getContext(), NoConnectionActivity.class);
                startActivity(i);
            }
        });
    }

    public void getDailyForecast(int id, final String cityName) {
        final Call<List<DailyForecastModel>> request = ManagerAll.getInstance().getDailyForecastModel(id);
        request.enqueue(new Callback<List<DailyForecastModel>>() {
            @Override
            public void onResponse(Call<List<DailyForecastModel>> call, Response<List<DailyForecastModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() != 0) {
                        List<TahminItem> tahminItems = response.body().get(0).getTahmin();

                        pie(cityName,String.valueOf(tahminItems.get(response.body().size()).getSicaklik()),
                                String.valueOf(tahminItems.get(response.body().size()).getNem()),
                                String.valueOf(tahminItems.get(response.body().size()).getRuzgarHizi()),
                                String.valueOf(tahminItems.get(response.body().size()).getRuzgarYonu()));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<DailyForecastModel>> call, Throwable t) {

            }
        });
    }


    //Grafiksel Tasarım için

    public void pie(String cityName,String sicaklik,String nem, String ruzgarHizi, String ruzgarYonu){
        pieChart = view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(75f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(40f);
        pieChart.setCenterText(cityName+"\n"+sicaklik+"°C");

        nemText.setText(nem);
        ruzgarhiziText.setText(ruzgarHizi);
        ruzgaryonuText.setText(ruzgarYonu);

        pieChart.setCenterTextSize(30f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(Float.parseFloat(sicaklik),"Sıcaklık"));
        yValues.add(new PieEntry(Float.parseFloat(nem),"Nem"));
        yValues.add(new PieEntry(Float.parseFloat(ruzgarHizi),"Rüzgar"));

        pieChart.animateY(1000, Easing.EaseInOutCirc);

        PieDataSet dataSet = new PieDataSet(yValues,"Weather");
        dataSet.setSliceSpace(3f);
        dataSet.setSliceSpace(5f);

        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);

    }




}
