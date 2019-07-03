package com.example.johnraviv.twentyquestionv12.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.johnraviv.twentyquestionv12.R;
import com.example.johnraviv.twentyquestionv12.services.DBhelper;
import com.example.johnraviv.twentyquestionv12.services.ExampleDialog;
import com.example.johnraviv.twentyquestionv12.services.User;

import java.util.ArrayList;

//HIGHSCORE BOARD USING SQLITE
public class HighScore extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    String textFileName = "";
    ListView listView;
    int userScore;
    ArrayList<String> scores;

    DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_high_score);
        listView = findViewById(R.id.list);
        userScore = getIntent().getIntExtra("score",0);
        scores =new ArrayList<>();
        dBhelper = new DBhelper(this);
        openDialog();

    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username) {
        textFileName = username;
        dBhelper.addHandler(new User(textFileName,userScore));
        String score[] = dBhelper.loadHandler().split(System.getProperty("line.separator"));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        for(int i=0;i<score.length;i++){
            arrayAdapter.add(score[i]);
        }
        listView.setAdapter(arrayAdapter);

    }

    public void restart(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
