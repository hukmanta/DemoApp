package com.robpercival.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view){

        EditText username = (EditText) findViewById(R.id.yourNameTextField);
        Toast.makeText(MainActivity.this, "Hi "+username.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }

}
