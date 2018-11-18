package com.example.mosaab.weather.ViewHolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mosaab.weatherJson.R;

public class Splash_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__view);

        Thread Splash = new Thread(){
            @Override
            public void run(){

                try {
                    sleep(2000); // the time of holding the splash
                    Intent splash = new Intent(Splash_View.this,MainActivity.class);
                    startActivity(splash);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Splash.start();

    }
}
