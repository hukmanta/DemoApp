package com.hukman.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
     TextView textView;

    public void setLanguage(String language){
        sharedPreferences = this.getSharedPreferences("com.hukman.languagepreferences", Context.MODE_PRIVATE);
        textView.setText(language);
        sharedPreferences.edit().putString("preferences", language).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.hukman.languagepreferences", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("preferences", "");

        if (text == ""){

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose  Your Language")
                .setMessage("Which is your prefered language?").setPositiveButton("English", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setLanguage("English");
            }
        }).setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setLanguage("Spanish");
            }
        }).show();} else{
            textView.setText(text);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.english:
                setLanguage("English");
                Toast.makeText(this, "Preferences changed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.spanish:
                setLanguage("Spanish");
                Toast.makeText(this, "Preferences changed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}