package com.example.weatherapp.data;

import android.provider.BaseColumns;

public class DbContract {

    public static final class MenuEntry implements BaseColumns{
        //Şehir durumlarının kontrolü için veritanabı bilgileri
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_IL = "il";
        public static final String COLUMN_PLAKA = "plaka";
        public static final String COLUMN_DURUM = "durum";
    }
}
