package com.hukman.numbershape;

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

    class Number {

        int number;

        public boolean isTriangular(){

            int x = 1;

            int triangularNumber = 1;

            while (triangularNumber < number) {

                x++;

                triangularNumber = triangularNumber + x;
            }

            if (triangularNumber == number)

                return true;

            else

                return false;
        }

        public boolean isSquare(){

            double squareRoot = Math.sqrt(number);

            if (Math.floor(squareRoot) == squareRoot)

                return true;

            else

                return false;
        }
    }

    public void testNumber(View view) {

        Number myNumber = new Number();

        String message="";

        EditText text = findViewById(R.id.InputEditText);

        if (text.getText().toString().isEmpty()) {

            message = "Please Enter a Number";

        } else {

            myNumber.number = Integer.parseInt(text.getText().toString());

            message = myNumber.number + " ";

            if (myNumber.isTriangular()) {

                if (myNumber.isSquare()) {

                    message = message + "is both Triangular Number and Square Number ";

                } else {

                    message = message + "is Triangular Number but Not Square Number ";

                }
            } else {

                if (!myNumber.isTriangular()) {

                    if (myNumber.isSquare()) {

                        message = message + "is not Triangular Number but Square Number ";

                    } else {

                        message = message + "is Neither Triangular Number and Square Number ";

                    }
                }
            }
        }
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();


    }
}
