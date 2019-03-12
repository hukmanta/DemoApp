package com.hukman.currencyconverter;

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

    public void convertClicked(View view){
        EditText text = (EditText) findViewById(R.id.inputText);
        Double angkaDolar = Double.parseDouble(text.getText().toString());
        Double angkaRupiah = angkaDolar * 14492.05;
        Toast.makeText(MainActivity.this, "Converted Value is Rp"+ String.format("%.2f", angkaRupiah), Toast.LENGTH_LONG).show();
    }
}
