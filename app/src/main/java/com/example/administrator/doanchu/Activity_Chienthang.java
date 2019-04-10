package com.example.administrator.doanchu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Chienthang extends AppCompatActivity {
    Button button;
    MediaPlayer mediaPlayer;
    TextView nsthem ,result;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chienthang);
        nsthem = (TextView)findViewById(R.id.nsthem);
        result = (TextView)findViewById(R.id.result);
        button=(Button)findViewById(R.id.tieptuc);
        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("Score_game");
        if(score<10)
        {
            nsthem.setText("Too Bad");
            result.setText("Score: "+(score));
        }
        if(score>=10)
        {
            nsthem.setText("Good");
            result.setText("Score: "+(score));
        }
        if(score>=15)
        {
            result.setText("Score: "+(score));
            nsthem.setText("Very Good !");
        }
        if(score>18)
        {
            result.setText("Score: "+(score));
            nsthem.setText(" Excellent");
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer =MediaPlayer.create(Activity_Chienthang.this,R.raw.music_end);
        mediaPlayer.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();

                Intent intent = new Intent(Activity_Chienthang.this, Activity_menu.class);
                startActivity(intent);

            }
        });
    }
}
