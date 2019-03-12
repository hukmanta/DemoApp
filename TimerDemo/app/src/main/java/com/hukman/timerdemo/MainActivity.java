package com.hukman.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long miliSecondUntilDone) {
                // countdown is counting down (every second)
                Log.d("Second Left", String.valueOf(miliSecondUntilDone / 1000));

            }

            @Override
            public void onFinish(){
                // counter is finish ( every 10 second)
                Log.d("Done!", "Countdown Timer Finished");
            }
        }.start();

        /*final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //Insert cod to be run every second

                Log.i("Runnable has run!", "a seccond has passed");

             handler.postDelayed(this, 1000);

            }
        };
        handler.post(run);*/
    }
}
