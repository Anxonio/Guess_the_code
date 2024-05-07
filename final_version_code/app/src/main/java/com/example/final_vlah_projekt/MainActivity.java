package com.example.final_vlah_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[4];
    ImageView img;
    TextView tvScore;
    MediaPlayer player_right, player_wrong;

    int counter_wrong = 0;



    boolean game_over = false;
    String fetch_score;
    int[] images = {R.drawable.c_nova, R.drawable.cp_nova, R.drawable.dart_nova,
            R.drawable.java_nova, R.drawable.js_nova,R.drawable.slika_r, R.drawable.slika_csharp, R.drawable.slika_css,
            R.drawable.slika_elixir, R.drawable.slika_erlang, R.drawable.slika_golang, R.drawable.slika_html, R.drawable.slika_kotlin,
            R.drawable.slika_lua, R.drawable.slika_matlab, R.drawable.slika_objc, R.drawable.slika_pascal, R.drawable.slika_perl, R.drawable.slika_php,
            R.drawable.slika_powershell, R.drawable.slika_python, R.drawable.slika_ruby
    };
    int random_btn_number, random_img_number, img_tag;
    int score;
    String fetch_answer;
    HashMap<Integer, String> map;
    ArrayList<Integer> used_images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = new HashMap<>();
        map.put(images[0], "C");
        map.put(images[1], "C++");
        map.put(images[2], "Dart");
        map.put(images[3], "Java");
        map.put(images[4], "JavaScript");
        map.put(images[5],"R");
        map.put(images[6],"C#");
        map.put(images[7],"Css");
        map.put(images[8],"Elixir");
        map.put(images[9],"Erlang");
        map.put(images[10],"Go");
        map.put(images[11],"Html");
        map.put(images[12],"Kotlin");
        map.put(images[13],"Lua");
        map.put(images[14],"Matlab");
        map.put(images[15],"Objective-C");
        map.put(images[16],"Pascal");
        map.put(images[17],"Perl");
        map.put(images[18],"PHP");
        map.put(images[19],"Powershell");
        map.put(images[20],"Python");
        map.put(images[21],"Ruby");

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        img = findViewById(R.id.img);
        player_right = MediaPlayer.create(this, R.raw.right);
        player_wrong = MediaPlayer.create(this, R.raw.wrong);
        fetch_score = getString(R.string.key_score);
        tvScore = findViewById(R.id.tvScore1);

        Random random = new Random();


        imageSetUp(random); // nadodano zadnje 3.4
        buttonSetUp(random); // nadodano zadnje 3.4



        



        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttons[0], random);
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttons[1], random);
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttons[2], random);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttons[3], random);
            }
        });
    }

    public void imageSetUp(Random random) {

            do {
                random_img_number = random.nextInt(images.length);
            } while (used_images.contains(images[random_img_number]));



            img.setImageResource(images[random_img_number]);
            img.setTag(images[random_img_number]);
            img_tag = (int)img.getTag();
            fetch_answer = map.get(img_tag);
            used_images.add(img_tag);


    }

    public void setGame_over(){
         Intent intent = new Intent(MainActivity.this, GameOver.class);
         intent.putExtra("score",String.valueOf(score));
         startActivity(intent);
    }

    public void buttonSetUp(Random random) {
        random_btn_number = random.nextInt(buttons.length);
        buttons[random_btn_number].setText(fetch_answer);
        ArrayList<String> answers = new ArrayList<>(map.values());
        answers.remove(fetch_answer);
        Collections.shuffle(answers);
        for (int i = 0; i < buttons.length; i++) {
            if (i != random_btn_number) {
                buttons[i].setText(answers.get(i));
            }
        }
    }

    public void checkAnswer(Button selectedButton, Random random) {


        if (selectedButton.getText().toString().equals(fetch_answer)) {
                //Ako je rezultat tocan
            player_right.start();
            score = score+50;
            tvScore.setText(fetch_score + " " + String.valueOf(score));
        player_right.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageSetUp(random);
                buttonSetUp(random);
            }
        });

        }
        if(!(selectedButton.getText().toString().equals(fetch_answer))) {
    ++counter_wrong;
            if (score > 0){
                player_wrong.start();
                score = score - 20;
                tvScore.setText(fetch_score + " " + String.valueOf(score));
                player_wrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        imageSetUp(random);
                        buttonSetUp(random);

                    }
                });

        }
            if(score<=0){
                score = 0;
                player_wrong.start();
                tvScore.setText(fetch_score + " " + String.valueOf(score));
                player_wrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        imageSetUp(random);
                        buttonSetUp(random);

                    }
                });


            }

            if(counter_wrong==3){
                if(score-20<0){
                    score = 0;
                }

                if(score-20>0){
                    score-=20;
                }
                player_wrong.start();
                tvScore.setText(fetch_score + " " + String.valueOf(score));
                player_wrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        setGame_over();

                    }
                });
            }

        }




        if(used_images.size() == map.size()){
            setGame_over();
        }else{
            //imageSetUp(random);
            //buttonSetUp(random);
        }


    }
}
