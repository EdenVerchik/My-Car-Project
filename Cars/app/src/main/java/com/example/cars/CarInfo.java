package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CarInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        Bundle bundle = getIntent().getExtras();

        TextView brandOutView = (TextView) findViewById(R.id.brand_name_output);
        TextView modelOutView = (TextView) findViewById(R.id.model_name_output);
        TextView priceOutView = (TextView) findViewById(R.id.price_output);
        TextView yearOutView = (TextView) findViewById(R.id.year_output);
        ImageView imageOutView = (ImageView) findViewById(R.id.image_output);


        if (bundle != null) {
            String carBrand = bundle.getString("carBrandSelected");
            String carModel = bundle.getString("carModelSelected");
            String carYear = bundle.getString("carPriceSelected");
            String carPrice = bundle.getString("carYearSelected");
            int carImage = bundle.getInt("carImageSelected");

            brandOutView.setText("Brand: " + carBrand);
            modelOutView.setText("Model: " + carModel);
            priceOutView.setText("Price: " + carYear);
            yearOutView.setText("Year: " + carPrice);
            imageOutView.setImageResource(carImage);
        }
        else {

        }

    }
}