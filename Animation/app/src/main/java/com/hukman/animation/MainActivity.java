package com.hukman.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageView bart = (ImageView) findViewById(R.id.bart);

        bart.setScaleX(0.5f);

        bart.setScaleY(0.5f);

        bart.setAlpha(0.2f);

        bart.setRotation(90f);
    }

    public void fade(View view){

        ImageView bart = (ImageView) findViewById(R.id.bart);

       // ImageView homer = (ImageView) findViewById(R.id.homer);

       // if (bart.getAlpha() == 1f){

            bart.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .rotation(0f)
                    .setDuration(2000);

         //   homer.animate().alpha(1f).setDuration(2000);

       /* } else {

            bart.animate().alpha(1f).setDuration(2000);

            homer.animate().alpha(0f).setDuration(2000);

        }
*/
    }

    /*public void fade2(View view) {

        ImageView bart = (ImageView) findViewById(R.id.bart);

        ImageView homer = (ImageView) findViewById(R.id.homer);

        homer.animate().alpha(0f).setDuration(2000);

        bart.animate().alpha(1f).setDuration(2000);
    }*/

    }
