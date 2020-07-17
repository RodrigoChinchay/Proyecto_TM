package com.tmz.aplicacionzuniga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    MediaPlayer mp;
    int pause;
    private Button buttonchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonchat=findViewById(R.id.buttonchat);

        buttonchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
    public void play(View view){
        if(mp==null) {
            mp = MediaPlayer.create(this, R.raw.at);
            mp.start();
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }else if(!mp.isPlaying()){
            mp.seekTo(pause);
            mp.start();
            Toast.makeText(this, "Renaudando", Toast.LENGTH_SHORT).show();
        }
    }
    public void pause(View view){
        if(mp !=null) {
            mp.pause();
            pause =mp.getCurrentPosition();
            Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        }
    }
    public void stop(View view){
        mp.stop();
        mp=null;
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

}
