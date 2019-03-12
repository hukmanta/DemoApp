package com.hukman.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.sql.StatementEvent;

public class MainActivity extends AppCompatActivity {

    boolean gameIsActve = true;

    int activePlayer = 0;
    // 0 = yellow, 1 = red, 2= empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tapCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tapCounter] == 2 && gameIsActve) {

            gameState[tapCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotationBy(135f).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameIsActve = false;
                    //code bug
                    LinearLayout winningMessageLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    TextView winner = (TextView) findViewById(R.id.winnerMessage);

                    if (gameState[winningPosition[0]] == 0) {

                        winner.setText("Pemain Kuning adalah Pemenangnya,\n Pemain Merah silahkan coba lagi");

                    } else {

                        winner.setText("Pemain Merah adalah Pemenangnya,\n Pemain Kuning silahkan coba lagi");

                    }
                    winningMessageLayout.setVisibility(View.VISIBLE);
// code bug

                } else if (draw()){

                    LinearLayout winningMessageLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    TextView winner = (TextView) findViewById(R.id.winnerMessage);

                    winner.setText("Draw Nih Yeee...");

                    winningMessageLayout.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view) {

        int[] RestartState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

        gameIsActve = true;

        gameState = RestartState;

        LinearLayout winningMessageLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

        winningMessageLayout.setVisibility(View.INVISIBLE);

        resetImage();

    }

   public void resetImage() {

        ImageView image = findViewById(R.id.imageView);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView2);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView3);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView4);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView5);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView6);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView7);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView8);

        image.setImageDrawable(null);

        image = findViewById(R.id.imageView9);

        image.setImageDrawable(null);
    }

    public boolean draw(){

        boolean draw = true;

        for (int slot: gameState){
            if (slot == 2)
                draw = false;
        }

        return  draw;

    }

}
