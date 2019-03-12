package com.hukman.imagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changePicture(View view){
        ImageView x = (ImageView) findViewById(R.id.imageView);
        if (x.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.logo_python).getConstantState())
            x.setImageResource(R.drawable.logo_java);
        else
            x.setImageResource(R.drawable.logo_python);
    }
}
