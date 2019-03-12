package com.hukman.lebihataukurang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int angkaYangHarusDitebak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        angkaYangHarusDitebak = (int) (Math.random()* 100 + 1);
    }

    public void cekJawabanClicked(View view){
        EditText tebakan = (EditText) findViewById(R.id.inputTebakanText);

        int angkaTebakan = Integer.parseInt(tebakan.getText().toString());

        if (angkaTebakan > angkaYangHarusDitebak){

            makeToast("Tebakan Anda Kelebihan");

        } else if (angkaTebakan < angkaYangHarusDitebak){

           makeToast("Tebakan Anda Kekurangan");

        } else {

            makeToast("Selamat Anda Menebak Benar");

        }
    }

    public void acakNomor(View view){

        angkaYangHarusDitebak = (int) (Math.random()* 100 + 1);

        Toast.makeText(MainActivity.this, "Angka Telah diacak", Toast.LENGTH_LONG).show();
    }

    public void makeToast(String string){

        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();

    }

}
