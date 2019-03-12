package com.hukman.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> location = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.hukman.memorableplaces", Context.MODE_PRIVATE);
        ArrayList<String> latitude = new ArrayList<>();
        ArrayList<String> longtitude = new ArrayList<>();

        places.clear();
        latitude.clear();
        longtitude.clear();
        location.clear();

        try {
            places = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>())));
            latitude = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("latitude", ObjectSerializer.serialize(new ArrayList<String>())));
            longtitude = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longtitude", ObjectSerializer.serialize(new ArrayList<String>())));
            if (places.size() > 0 && latitude.size() > 0 && longtitude.size() > 0) {
                if (places.size() == latitude.size() && latitude.size() == longtitude.size()) {
                    for (int i = 0; i < latitude.size(); i++) {
                        location.add(new LatLng(Double.parseDouble(latitude.get(i)), Double.parseDouble(longtitude.get(i))));
                    }
                }
            } else {
                places.add("Add a new Place ...");
                location.add(new LatLng(0, 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        listView = findViewById(R.id.placesListView);


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, places);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("placenumber", position);

                startActivity(intent);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ArrayList<String> latitude = new ArrayList<>();
        ArrayList<String> longtitude = new ArrayList<>();


        try {
            sharedPreferences.edit().putString("places", ObjectSerializer.serialize(places)).apply();
            for (LatLng coordinate : location) {
                latitude.add(Double.toString(coordinate.latitude));
                longtitude.add(Double.toString(coordinate.longitude));
            }
            sharedPreferences.edit().putString("latitude", ObjectSerializer.serialize(latitude)).apply();
            sharedPreferences.edit().putString("longtitude", ObjectSerializer.serialize(longtitude)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
