package com.example.weatherapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.activities.LoadActivity;
import com.example.weatherapp.activities.SelectCityActivity;
import com.example.weatherapp.data.DbHelper;
import com.example.weatherapp.models.CityStatesModel;
import com.example.weatherapp.models.SuperClass;

import java.util.ArrayList;
import java.util.HashMap;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> all_cities_list;
    DbHelper dbHelper;

    public CitiesAdapter(Context context, ArrayList<HashMap<String, String>> all_cities_list) {
        this.context = context;
        this.all_cities_list = all_cities_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_item_layout, null);

        dbHelper = new DbHelper(context);

        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //holder.cityName.setText((cities.get(position).getCityName()));

        holder.cityName.setText(all_cities_list.get(position).get("il"));
        if (all_cities_list.get(position).get("durum").equals("1")) {
            holder.cityCheckBox.setChecked(true);
        } else {
            holder.cityCheckBox.setChecked(false);
        }
        holder.cityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.cityCheckBox.isChecked()) {
                    dbHelper.activeCityWithName("1", all_cities_list.get(position).get("il"));
                } else {
                    if (all_cities_list.get(position).get("il").equalsIgnoreCase(LoadActivity.locationCity)) {
                        if (!holder.cityCheckBox.isChecked()) {
                            Toast.makeText(context, "Konumunuz açık olduğundan bu şehir kaldırılamıyor!", Toast.LENGTH_SHORT).show();
                            holder.cityCheckBox.setChecked(true);
                        }
                    }else
                    dbHelper.activeCityWithName("0", all_cities_list.get(position).get("il"));


                    if (dbHelper.getActiveRowCount() == 0) {
                        Toast.makeText(context, "Hiç bir şehir kalmadığı için otomatik olarak İzmir eklendi!", Toast.LENGTH_LONG).show();
                        dbHelper.activeCityWithName("1", "İzmir");
                        Intent i = new Intent(context, SelectCityActivity.class);
                        context.startActivity(i);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return all_cities_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityName;
        CheckBox cityCheckBox;

        //itemview ile listview in her elamanu için layout ile oluşturduğumuz view tanımlaması
        public ViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            cityCheckBox = itemView.findViewById(R.id.cityCheckBox);

        }
    }
}
