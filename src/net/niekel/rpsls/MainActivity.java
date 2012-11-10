package net.niekel.rpsls;

import java.util.Random;

import android.content.res.Configuration;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

public class MainActivity extends Activity
{
    private int score1 = 0;
    private int score2 = 0;
    private int player = -1;
    private int android = -1;

    private void printScore()
    {
        TextView v = (TextView) findViewById(R.id.score);

        v.setText(Integer.toString(score1)+"-"+Integer.toString(score2));
    }

    private void printChoices()
    {
        TextView v1 = (TextView) findViewById(R.id.message1);
        TextView v2 = (TextView) findViewById(R.id.message2);

        v1.setText("You: "+getname(player));
        v2.setText("Android: "+getname(android));
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation ==
                                    Configuration.ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.portrait);
        }
        else
        {
            setContentView(R.layout.landscape);
        }
        printScore();
        printChoices();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.landscape);
        }
        else
        {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                setContentView(R.layout.portrait);
            }
        }
        printScore();
        printChoices();
    }

    private String getname(int i)
    {
        if (i == 0)
        {
            return "Rock";
        }
        if (i == 1)
        {
            return "Spock";
        }
        if (i == 2)
        {
            return "Paper";
        }
        if (i == 3)
        {
            return "Lizard";
        }
        if (i ==4)
        {
            return "Scissors";
        }
        return "";
    }

    private int evaluateChoices(int a, int p)
    {
        int score = (5 + p - a) % 5;
        if ((score == 1) || (score == 2)) { return 1; } // player wins
        else
        {
            if (score == 0) { return 0; } // draw
            else { return 2; } // android wins
        }
    }

    public void resetClick(View view)
    {
        score1 = 0;
        score2 = 0;
        printScore();
    }

    public void buttonClick(View view)
    {
        ImageButton buttonClicked = (ImageButton) view;
        TextView whoWon = (TextView) findViewById(R.id.message3);

        String tag = (String) view.getTag();
        player = Integer.parseInt(tag);
        android = new Random().nextInt(5);
        int winner = evaluateChoices(android, player);

        printChoices();

        if (winner == 1) // player wins
        {
            score1 += 1;
            whoWon.setText("Player wins!");
        }
        else
        {
            if (winner == 2) // android wins
            {
                score2 += 1;
                whoWon.setText("Android wins!");
            }
            else { whoWon.setText("Draw!"); }
        }
        printScore();
    }
}
