package com.example.bmsit.start;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bmsit.R;
import com.example.bmsit.SplashScreen.Splash_Screen;


public class startactivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(startactivity.this, Splash_Screen.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },5000);




        textView = findViewById(R.id.tv);

        Animation spalshscreen = AnimationUtils.loadAnimation(this,R.anim.spalshscreen);
        textView.startAnimation(spalshscreen);
    }


}
