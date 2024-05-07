package com.example.final_vlah_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Game_start extends AppCompatActivity {




    ImageView startGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);


        startGame = (ImageView) findViewById(R.id.startGame);
        startGame.setImageResource(R.drawable.resiz_btn);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game_start.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}