package com.hukman.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ArrayList<String> myFriend = new ArrayList<String>();

        myFriend.add("Gintha");
        myFriend.add("Syamsuar");
        myFriend.add("Ibnu");
        myFriend.add("Fashbir");
        myFriend.add("Elsen");

        ListView myFriendListView = findViewById(R.id.myFriendList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, myFriend );

        myFriendListView.setAdapter(arrayAdapter);

        myFriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Hello "+ (String) adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
