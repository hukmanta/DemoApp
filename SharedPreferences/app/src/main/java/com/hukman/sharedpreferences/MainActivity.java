package com.hukman.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.hukman.sharedpreferences", Context.MODE_PRIVATE);

        /*ArrayList<String> friends = new ArrayList<>();

        friends.add("Andri");
        friends.add("Hukman");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ArrayList<String> newFriend = new ArrayList<>();

        try {
            newFriend =(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String friend: newFriend) {
            Log.i("Friends: ", friend.toString());
        }

        /*sharedPreferences.edit().putString("username", "Hukman Thayib").apply();

        String username = sharedPreferences.getString("username", "");

        Log.i("Username: ", username);*/

    }
}
