package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //cross 0: ,circle 1:,2 null:
    int activePlayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;
    int count=0;
    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.green1);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red1);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);
            for (int[] winningposition : winningpositions) {
                String message = "";
                Button playAgain=(Button)findViewById(R.id.button);
                TextView winner=(TextView)findViewById(R.id.textView);
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2)
                {
                    gameactive=false;
                    if (activePlayer == 0) {
                        message = " circle has won";
                    } else {
                        message = "cross has won";
                    }
                    winner.setText(message);
                    playAgain.setVisibility(View.VISIBLE);
                    winner.setVisibility(View.VISIBLE);

                }
            }


        }
    }
    public void playagain(View view)
    {
        Button playAgain=(Button)findViewById(R.id.button);
        TextView winner=(TextView)findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        activePlayer=0;
        gameactive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}