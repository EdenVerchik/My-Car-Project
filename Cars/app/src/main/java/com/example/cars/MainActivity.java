package com.example.cars;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cars.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static MainActivity context;

    ListView carsListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        MainActivity.context = MainActivity.this;
        PreferencesLoader.restoreDataFromSharedPreferences();

        // Put the data in the ListView using customAdapter

        carsListView = (ListView)findViewById(R.id.listOfCars);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),
                PreferencesLoader.brandList.toArray(new String[0]),
                PreferencesLoader.imageList.toArray(new Integer[0]),
                PreferencesLoader.modelsList.toArray(new String[0]),
                PreferencesLoader.yearsList.toArray(new String[0]),
                PreferencesLoader.pricesList.toArray(new String[0]));
        carsListView.setAdapter(customAdapter);

        // Set OnClick on ListView
        carsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Object item = adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, CarInfo.class);
                //based on the item clicked, add info to intent
                intent.putExtra("carBrandSelected", PreferencesLoader.brandList.get(i));
                intent.putExtra("carModelSelected", PreferencesLoader.modelsList.get(i));
                intent.putExtra("carYearSelected", PreferencesLoader.yearsList.get(i));
                intent.putExtra("carPriceSelected", PreferencesLoader.pricesList.get(i));
                intent.putExtra("carImageSelected", PreferencesLoader.imageList.get(i));
                startActivity(intent);
            }
        });
    }

    public static MainActivity getAppContext() {
        return MainActivity.context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemenft
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewCar(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewCar.class);
        startActivity(intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        settingsItem.setVisible(false);
        return false;
    }
}