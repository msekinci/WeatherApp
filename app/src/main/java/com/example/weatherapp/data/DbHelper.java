package com.example.weatherapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getSimpleName();

    private Resources mResources;
    private static final String DATABASE_NAME = "iller.db";
    private static final int DATABASE_VERSION = 14;
    SQLiteDatabase db;

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mResources = context.getResources();
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CITY_TABLE = "CREATE TABLE " + DbContract.MenuEntry.TABLE_NAME + " (" +
                DbContract.MenuEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.MenuEntry.COLUMN_PLAKA + " TEXT UNIQUE NOT NULL, " +
                DbContract.MenuEntry.COLUMN_IL + " TEXT UNIQUE NOT NULL ," +
                DbContract.MenuEntry.COLUMN_DURUM + " TEXT NOT NULL" + " );";

        db.execSQL(SQL_CREATE_CITY_TABLE);
        try {
            readDataToDb(db);
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS gps;");
        db.execSQL("DROP TABLE IF EXISTS "+DbContract.MenuEntry.TABLE_NAME);

        onCreate(db);

    }

    //Aktif olan şehir sayısını getir
    public int getActiveRowCount() {
        // Bu method bu uygulamada kullanılmıyor ama her zaman lazım olabilir.Tablodaki row sayısını geri döner.
        //Login uygulamasında kullanacağız
        String countQuery = "SELECT  * FROM " + DbContract.MenuEntry.TABLE_NAME+ " WHERE durum = " + "1";
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }

    //Şehrin adına göre durum güncelleme
    public void activeCityWithName(String durum,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(DbContract.MenuEntry.COLUMN_DURUM, durum);

        // updating row
        db.update(DbContract.MenuEntry.TABLE_NAME, values, "il = "+"?",
                new String[] { String.valueOf(name) });
    }


    //Tüm şehirler ve durumları
    public ArrayList<HashMap<String, String>> allCities(){

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DbContract.MenuEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> cityList = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                cityList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return cityList;
    }

    //Search yapılan şehirler ve durumları
    public ArrayList<HashMap<String, String>> searchCities(String string){

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DbContract.MenuEntry.TABLE_NAME+" WHERE "+DbContract.MenuEntry.COLUMN_IL+" LIKE '%"+string+"%'";
        Log.e("sele",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> cityList = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                cityList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return cityList;
    }

    //Aktif şehir ve durumları
    public ArrayList<HashMap<String, String>> activeCities(){

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + DbContract.MenuEntry.TABLE_NAME+ " WHERE durum = " + "1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> cityList = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                cityList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return cityList;
    }

    //Hazır JSON veriyi ayırma ve database'e yazma
    private void readDataToDb(SQLiteDatabase db) throws IOException, JSONException{
        final String PLAKA = "plaka";
        final String IL = "il";
        final String DURUM = "durum";
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i<menuItemsJsonArray.length() ; ++i){

                String plaka;
                String il;
                String durum;

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);

                plaka = menuItemObject.getString(PLAKA);
                il = menuItemObject.getString(IL);
                durum = menuItemObject.getString(DURUM);

                ContentValues menuValues = new ContentValues();

                menuValues.put(DbContract.MenuEntry.COLUMN_PLAKA, plaka);
                menuValues.put(DbContract.MenuEntry.COLUMN_IL, il);
                menuValues.put(DbContract.MenuEntry.COLUMN_DURUM, durum);

                db.insert(DbContract.MenuEntry.TABLE_NAME, null, menuValues);

                Log.i("DATABASE", DbContract.MenuEntry.TABLE_NAME+" TABLOSU OLUSTURULDU" + menuValues);


            }
        }catch (JSONException e){
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    //Dosyadan JSON veri çekme
    private String readJsonDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = mResources.openRawResource(R.raw.iller);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        }finally{
            if (inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
