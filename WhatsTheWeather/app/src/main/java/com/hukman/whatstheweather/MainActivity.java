package com.hukman.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.cityInput);
        resultTextView = findViewById(R.id.textView2);
    }


    public void findWeather(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(input.getWindowToken(), 0);

            try {
                String encodeUrl = URLEncoder.encode(input.getText().toString(), "UTF-8");
                String url = "http://api.openweathermap.org/data/2.5/weather?q="+ encodeUrl  + "&APPID=17de6bd855acf0db4fa430ec53a5d038";
                JSONDownload task = new JSONDownload();
                task.execute(url);

            }
         catch(Exception e){
            e.printStackTrace();
             Toast.makeText(getApplicationContext(), "Can't get weather info", Toast.LENGTH_LONG).show();
        }
    }
    //Log.i("Content JSON: ", weatherInfo);


    public class JSONDownload extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }

                return result;


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                String message = "";

                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");

                JSONArray array = new JSONArray(weatherInfo);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject jsonPart = array.getJSONObject(i);
                    String main = "";
                    String description = "";

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != "") {

                        message += main + ": " + description + "\r\n";

                    }
                }

                if (message != "") {

                    resultTextView.setText(message);

                } else {

                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {

                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
            }


        }
    }
}