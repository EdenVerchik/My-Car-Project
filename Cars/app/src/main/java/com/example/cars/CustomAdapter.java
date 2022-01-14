package com.example.cars;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String brandList[];
    Integer imageList[];
    String modelList[];
    String yearsList[];
    String priceList[];
    ListView lv;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] brandList, Integer[] imageList, String[] modelList, String[] yearsList, String[] priceList) {
        this.context = applicationContext;
        this.brandList = brandList;
        this.imageList = imageList;
        this.modelList = modelList;
        this.yearsList = yearsList;
        this.priceList = priceList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return brandList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_list_view, null);
        TextView brandsListView = (TextView) view.findViewById(R.id.car_brand);
        ImageView imagesListView = (ImageView) view.findViewById(R.id.car_photo);
        TextView modelsListView = (TextView) view.findViewById(R.id.car_model);
        TextView yearsListView = (TextView) view.findViewById(R.id.car_year);

        brandsListView.setText(brandList[i]);
        imagesListView.setImageResource(imageList[i]);
        modelsListView.setText(modelList[i]);
        yearsListView.setText(yearsList[i]);

        ImageButton deleteImageView = (ImageButton)view.findViewById(R.id.delete_item);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView)parentRow.getParent().getParent().getParent();
                final int position = listView.getPositionForView(parentRow);

                PreferencesLoader.brandList.remove(position);
                PreferencesLoader.modelsList.remove(position);
                PreferencesLoader.yearsList.remove(position);
                PreferencesLoader.pricesList.remove(position);
                PreferencesLoader.imageList.remove(position);
                PreferencesLoader.saveDataToSharedPreferences();

                // Update the custom adapter
                CustomAdapter customAdapter = new CustomAdapter(context,
                        PreferencesLoader.brandList.toArray(new String[0]),
                        PreferencesLoader.imageList.toArray(new Integer[0]),
                        PreferencesLoader.modelsList.toArray(new String[0]),
                        PreferencesLoader.yearsList.toArray(new String[0]),
                        PreferencesLoader.pricesList.toArray(new String[0]));
                listView.setAdapter(customAdapter);
                CustomAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }
}