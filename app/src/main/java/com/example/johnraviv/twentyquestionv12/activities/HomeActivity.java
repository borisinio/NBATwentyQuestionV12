package com.example.johnraviv.twentyquestionv12.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.johnraviv.twentyquestionv12.R;


//SPLASH SCREEN - FIRST SCREEN ON STARTUP
public class HomeActivity extends AppCompatActivity {
    public static int splash_time = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(HomeActivity.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, splash_time);

    }
}
