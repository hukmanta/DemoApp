package com.hukman.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view){
        EditText name = (EditText) findViewById(R.id.yourNameTextField);
        Toast.makeText(MainActivity.this, "Hi "+name.getText().toString(),Toast.LENGTH_LONG).show();
    }
}
