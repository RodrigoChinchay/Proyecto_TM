# Proyecto_TM
RODRIGOCHINCHAY,JAMESCARBAJAL,KELLYVERONICA,JOSEZUÑIGA
paquete  com.example.proyecto_tm ;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.MediaController;
import android.widget.VideoView;

public class SplashActivity extends Activity {

    private final int DURACION_SPLASH = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        VideoView videoView=(VideoView) findViewById(R.id.vv_test);
        Uri path= Uri.parse("android.resource://" + getPackageName()+ "/"+R.raw.v1);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(path);
        videoView.start();



        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
