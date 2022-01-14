package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewCar extends AppCompatActivity {

    EditText brandNameInput;
    EditText modelNameInput;
    EditText carYearInput;
    EditText carPriceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);

        brandNameInput = (EditText)findViewById(R.id.brand_name_input);
        modelNameInput = (EditText)findViewById(R.id.model_name_input);
        carYearInput = (EditText)findViewById(R.id.year_input);
        carPriceInput = (EditText)findViewById(R.id.price_input);
    }

    public void finishNewCar(View view) {
        // Get the input strings from the UI
        String newCarBrand = brandNameInput.getText().toString();
        String newCarModel = modelNameInput.getText().toString();
        String newCarYear = carYearInput.getText().toString();
        String newCarPrice = carPriceInput.getText().toString();

        // Check if there is an empty field
        if (newCarBrand.equals("") || newCarModel.equals("") || newCarYear.equals("") || newCarPrice.equals(""))
        {
            Toast.makeText(AddNewCar.this, "There are empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesLoader.brandList.add(newCarBrand);
        PreferencesLoader.modelsList.add(newCarModel);
        PreferencesLoader.yearsList.add(newCarYear);
        PreferencesLoader.pricesList.add(newCarPrice);
        PreferencesLoader.imageList.add(R.drawable.car_title);
        PreferencesLoader.saveDataToSharedPreferences();

        // Send the strings to the next activity
        Intent intent = new Intent(AddNewCar.this, MainActivity.class);
        startActivity(intent);
    }
}