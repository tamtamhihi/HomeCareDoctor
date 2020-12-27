package com.example.homecaredoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    static int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.finish();
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                SplashScreen.this.startActivity(intent);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
