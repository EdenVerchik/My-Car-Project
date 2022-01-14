package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {
    TextView carBrand = (TextView) findViewById(R.id.car_brand);
    TextView carModel = (TextView) findViewById(R.id.car_model);
    TextView carYear = (TextView) findViewById(R.id.car_year);
    ImageView carPhoto = (ImageView) findViewById(R.id.car_photo);
    ImageButton deleteItem = (ImageButton) findViewById(R.id.delete_item);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

}
