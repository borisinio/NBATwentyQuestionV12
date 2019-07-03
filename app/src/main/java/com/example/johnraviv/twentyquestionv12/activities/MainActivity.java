package com.example.johnraviv.twentyquestionv12.activities;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import com.example.johnraviv.twentyquestionv12.R;


public class MainActivity extends AppCompatActivity {



    Button easyBtn, mediumBtn, hardBtn;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_main);
        //this will bind your MainActivity.class file with activity_main.


        intent = new Intent(MainActivity.this,QuestionActivity.class);

        easyBtn = findViewById(R.id.easyBtn);
        mediumBtn = findViewById(R.id.mediumBtn);
        hardBtn = findViewById(R.id.hardBtn);
    }

    public void start(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        startActivity(intent);
    }

    public void easyQuetion(View view) {
        intent.putExtra("level","easy");
        startActivity(intent);
    }

    public void mediumQuetion(View view) {
        intent.putExtra("level","medium");
        startActivity(intent);
    }

    public void hardQuetion(View view) {
        intent.putExtra("level","hard");
        startActivity(intent);
    }
}
