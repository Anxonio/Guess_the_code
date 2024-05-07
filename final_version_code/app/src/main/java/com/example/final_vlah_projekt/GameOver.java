package com.example.final_vlah_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;



public class GameOver extends AppCompatActivity {

    String game_over = "";
    String shareScore;
    TextView GameOver, tvStats, tvScore1;
    String score, myScore, guessing;

    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        shareScore = getString(R.string.key_shareScore);
        myScore = getString(R.string.key_myscore);
        guessing = getString(R.string.key_guessing);
        game_over = getString(R.string.key_score);
        btnShare = findViewById(R.id.btnShare);

        GameOver = findViewById(R.id.tvGameOver);

        tvScore1 = findViewById(R.id.tvScore1);


        Intent intent = getIntent();

        score = intent.getStringExtra("score");
        tvScore1.setText(game_over +" " + score);


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareScore();
            }
        });





    }

    public void shareScore() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, myScore + " " +  score + " " + guessing);

        startActivity(Intent.createChooser(shareIntent, shareScore));
    }
}