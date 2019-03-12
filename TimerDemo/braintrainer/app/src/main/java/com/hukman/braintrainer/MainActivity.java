package com.hukman.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    boolean isGameActive = true;
    TextView resultTextView;
    TextView pointTextView;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfTheCorrectAnswer;
    int score = 0;
    int numberOfQuestion;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    public void generateQuestion() {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Random ran = new Random();
        answers.clear();

        int a = ran.nextInt(21);
        int b = ran.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfTheCorrectAnswer = ran.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfTheCorrectAnswer) {
                answers.add(a + b);
            } else {

                incorrectAnswer = ran.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = ran.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(final View view) {
        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30 s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(view.INVISIBLE);
        isGameActive = true;

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long milisInFinished) {
                timerTextView.setText(String.valueOf(milisInFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Yout Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                playAgainButton.setVisibility(view.VISIBLE);
                isGameActive = false;
            }
        }.start();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        RelativeLayout gameRelativeLayout = findViewById(R.id.gameRelativeLayout);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {

        if (isGameActive) {
            if (view.getTag().toString().equals(Integer.toString(locationOfTheCorrectAnswer))) {

                score++;
                resultTextView.setText("Corret !");
            } else {
                resultTextView.setText("Wrong !");

            }
            numberOfQuestion++;
            pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            generateQuestion();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        sumTextView = findViewById(R.id.sumTextview);
        resultTextView = findViewById(R.id.resultTextview);
        pointTextView = findViewById(R.id.pointTextview);
        timerTextView = findViewById(R.id.timerTextview);

        //generateQuestion();

        //playAgain(findViewById(R.id.playAgainButton));

    }
}
