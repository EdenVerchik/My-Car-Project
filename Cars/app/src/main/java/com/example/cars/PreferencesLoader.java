package com.example.cars;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class PreferencesLoader {
    public static ArrayList<String> brandList = new ArrayList<String>(Arrays.asList("Mazda", "Audi", "Hyundai", "Tesla", "Toyota", "BMW", "Honda", "Suzuki"));
    public  static ArrayList<Integer> imageList =new ArrayList<Integer>(Arrays.asList(R.drawable.mazda, R.drawable.audi, R.drawable.hyundai, R.drawable.tesla, R.drawable.toyota, R.drawable.bmw, R.drawable.honda, R.drawable.suzuki));
    public static ArrayList<String> modelsList = new ArrayList<String>(Arrays.asList("CX-3", "R8", "Ioniq", "Model 3", "RAV4", "i8", "Civic", "Swift"));
    public static ArrayList<String> yearsList = new ArrayList<String>(Arrays.asList("2021", "2017", "2021", "2022", "2022", "2019", "2019", "2018"));
    public static ArrayList<String> pricesList = new ArrayList<String>(Arrays.asList("120000₪", "700000₪", "200000₪", "300000₪", "250000₪", "688800₪", "160000₪", "105000₪"));


    public static void restoreDataFromSharedPreferences() {
        // Restore shared preferences data
        SharedPreferences pref = MainActivity.getAppContext().getPreferences(Context.MODE_PRIVATE);
//        pref.edit().clear().apply();
        if (pref.getAll().size() != 0) {
            String lst = pref.getString("brandList", "");
            try {
                JSONArray jsonArr = new JSONArray(lst);
                brandList = new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    if (!jsonArr.getString(i).equals("null"))
                        brandList.add(jsonArr.getString(i));
                }
                lst = pref.getString("modelsList", "");
                jsonArr = new JSONArray(lst);
                modelsList = new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    if (!jsonArr.getString(i).equals("null"))
                        modelsList.add(jsonArr.getString(i));
                }
                lst = pref.getString("yearsList", "");
                jsonArr = new JSONArray(lst);
                yearsList = new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    if (jsonArr.getString(i) != "null")
                        yearsList.add(jsonArr.getString(i));
                }
                lst = pref.getString("pricesList", "");
                jsonArr = new JSONArray(lst);
                pricesList = new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    if (!jsonArr.getString(i).equals("null"))
                        pricesList.add(jsonArr.getString(i));
                }
                lst = pref.getString("imageList", "");
                jsonArr = new JSONArray(lst);
                imageList = new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    if (!jsonArr.getString(i).equals("null"))
                        imageList.add(jsonArr.getInt(i));
                }
            } catch (JSONException e) {
                Log.e("", "Invalid JSON string", e);
                return;
            }
        }
    }


    public static void saveDataToSharedPreferences() {
        // save data to Shared Preferences
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        SharedPreferences pref= MainActivity.getAppContext().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        JSONArray jsonArray = new JSONArray(brandList);
        editor.putString("brandList", jsonArray.toString()).apply();
        jsonArray = new JSONArray(modelsList);
        editor.putString("modelsList", jsonArray.toString()).apply();
        jsonArray = new JSONArray(pricesList);
        editor.putString("pricesList", jsonArray.toString()).apply();
        jsonArray = new JSONArray(yearsList);
        editor.putString("yearsList", jsonArray.toString()).apply();
        jsonArray = new JSONArray(imageList);
        editor.putString("imageList", jsonArray.toString()).apply();
    }
}
