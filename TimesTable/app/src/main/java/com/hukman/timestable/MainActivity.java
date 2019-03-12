package com.hukman.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SeekBar timesTableSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesTableSeekbar = findViewById(R.id.timeTableSeekBar);

        listView = (ListView) findViewById(R.id.timeTableListView);

        timesTableSeekbar.setMax(20);

        timesTableSeekbar.setProgress(10);

        timesTableSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //create minimun value in seekbar
                int min = 1;
                int timeTable;

                if (progress < min) {
                    timeTable = min;
                    timesTableSeekbar.setProgress(min);
                } else {
                    timeTable = progress;
                }

                Log.i("Seekbar Value: ", Integer.toString(timeTable));
                generateTimesTable(timeTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(10);
    }

    public void generateTimesTable(int timeTable) {

        ArrayList<String> timesTableContent = new ArrayList<String>();

        for (int i = 1; i < 10; i++) {

            timesTableContent.add(Integer.toString(i * timeTable));

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);

        listView.setAdapter(arrayAdapter);

    }
}
