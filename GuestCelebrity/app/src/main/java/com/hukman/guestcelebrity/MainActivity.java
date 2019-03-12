package com.hukman.guestcelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebrityURL = new ArrayList<String>();
    ArrayList<String> celebrityName = new ArrayList<String>();
    int chosenCeleb = 0;
    int locationOfCorrectAnswer =0;
    String[] answers = new String[4];
    ImageView imageView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void celebChosen(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "Wrong! It was "+celebrityName.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }

        newQuestion();

    }

    public void newQuestion(){

        Random random = new Random();
        chosenCeleb = random.nextInt(celebrityName.size());

        ImageDownloader imageTask = new ImageDownloader();

        Bitmap celebImage;

        try {
            celebImage = imageTask.execute(celebrityURL.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        locationOfCorrectAnswer = random.nextInt(4);

        int incorrectAnswerLocation;

        for (int i =0; i<4; i++){

            if (i == locationOfCorrectAnswer){

                answers[i] = celebrityName.get(chosenCeleb);

            } else {
                incorrectAnswerLocation = random.nextInt(celebrityName.size());

                while (incorrectAnswerLocation == chosenCeleb){

                    incorrectAnswerLocation = random.nextInt(celebrityName.size());

                }
                answers[i] = celebrityName.get(incorrectAnswerLocation);
            }
        }

        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =findViewById(R.id.imageView);
        button0 = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        DownloadTask task = new DownloadTask();

        String result = null;


        try {
            result = task.execute("http://www.posh24.se/kandisar").get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] splitResult = result.split("<div class=\"sidebarContainer\">");

        Pattern p = Pattern.compile("img src=\"(.*?)\"");
        Matcher m = p.matcher(splitResult[0]);

        while (m.find()){
            celebrityURL.add(m.group(1));
        }

        p = Pattern.compile("alt=\"(.*?)\"");
        m = p.matcher(splitResult[0]);

        while (m.find()){
            celebrityName.add(m.group(1));
        }

        newQuestion();




    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reaader = new InputStreamReader(in);

                int data = reaader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reaader.read();

                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class ImageDownloader extends AsyncTask <String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
