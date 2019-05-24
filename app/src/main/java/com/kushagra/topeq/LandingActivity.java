package com.kushagra.topeq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        new Handler().postDelayed (new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (LandingActivity.this, MainActivity.class);
                startActivity(i);

                finish ();
            }
        }, SPLASH_TIME_OUT);
    }
}
