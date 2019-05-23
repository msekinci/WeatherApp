package com.example.weatherapp.data;

import android.provider.BaseColumns;

public class DbContract {

    public static final class MenuEntry implements BaseColumns{

        //şehir durumlarının kontrolü için veritanabı bilgileri
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_IL = "il";
        public static final String COLUMN_PLAKA = "plaka";
        public static final String COLUMN_DURUM = "durum";


        //Gps durumu kontrolü için veritabanı bilgisi
        public static final String GPS_TABLE_NAME = "GPS";
        public static final String GPS_DURUM = "gpsdurum";
        public static final String GPS_LAT ="gpslat";
        public static final String GPS_LON = "gpslon";


    }
}
