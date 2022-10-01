package com.OnlineShopping.login.com.hypermart.login.start;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.OnlineShopping.login.R;
import com.OnlineShopping.login.com.hypermart.login.account.SignIn;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gihan_activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(SplashScreen.this, SignIn.class);
                startActivity(splash);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
