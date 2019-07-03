package com.example.johnraviv.twentyquestionv12.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnraviv.twentyquestionv12.R;
import com.example.johnraviv.twentyquestionv12.classes.EasyQuestion;
import com.example.johnraviv.twentyquestionv12.classes.HardQuestion;
import com.example.johnraviv.twentyquestionv12.classes.MediumQuestion;
import com.example.johnraviv.twentyquestionv12.classes.Player;
import com.example.johnraviv.twentyquestionv12.classes.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class QuestionActivity extends AppCompatActivity {

    ArrayList<Player> players= new ArrayList<>();
    private int life, score;
    private TextView textScore, textTime;
    private ImageView flag, life1, life2, life3;
    private static ArrayAdapter adapter;
    private ArrayList<Integer> numbers;
    private Questions mQuestion;
    private static String answer;
    private static ListView listView;
    private static int counter;
    private CountDownTimer cTimer;
    private RadioButton rb1, rb2, rb3, rb4;
    private int indexStart, indexEnd;
    private RadioGroup rbGroup;
    private Boolean answered;
    FirebaseDatabase database;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

// database connection - firebase
        database = FirebaseDatabase.getInstance();

       try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }


        final String level = getIntent().getStringExtra("level");

        database = FirebaseDatabase.getInstance();



        life = 3;
        score = 0;
        counter = 0;

        numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        textScore = (TextView) findViewById(R.id.score);
        flag = (ImageView) findViewById(R.id.flag);
        listView = (ListView) findViewById(R.id.list);
        life1 = (ImageView) findViewById(R.id.life1);
        life2 = (ImageView) findViewById(R.id.life2);
        life3 = (ImageView) findViewById(R.id.life3);
        textTime = (TextView) findViewById(R.id.time);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.ans1);
        rb2 = findViewById(R.id.ans2);
        rb3 = findViewById(R.id.ans3);
        rb4 = findViewById(R.id.ans4);


// reference to Players DB
        DatabaseReference myRef = database.getReference("Players");

//choose difficulty level
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Player player = data.getValue(Player.class);
                    players.add(player);

                }
                switch (level) {
                    case "easy":
                        mQuestion = new EasyQuestion(players);
                        break;
                    case "medium":
                        mQuestion = new MediumQuestion(players);
                        break;
                    case "hard":
                        mQuestion = new MediumQuestion(players);
                        break;
                }


                setQuestion(numbers.get(0));
                textScore.setText(String.valueOf(score));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


// setup all answers, images and timer
    public void setQuestion(int num) {
        cancelTimer();
        startTimer();

        answer = mQuestion.getAnswers(num);
        flag.setImageResource(getResources().getIdentifier(mQuestion.getQuestions(num),"drawable",getPackageName()));
        System.out.println(mQuestion.getOptionsOne(num));
        rb1.setText(mQuestion.getOptionsOne(num));
        rb2.setText(mQuestion.getOptionsTwo(num));
        rb3.setText(mQuestion.getOptionsThree(num));
        rb4.setText(mQuestion.getOptionsFour(num));

    }

    public void setAnswer(String answer, int val) {
        counter++;
        textScore.setText(String.valueOf(val));

        score = val;
        if (counter == 20 || life == 0) {
            Toast.makeText(this, "GAME OVER!!", Toast.LENGTH_SHORT).show();
            endGame();
        }else {
            rbGroup.clearCheck();
            Toast.makeText(QuestionActivity.this, answer, Toast.LENGTH_SHORT).show();
            setQuestion(numbers.get(counter));
            System.out.println(counter);
        }
    }

    public void setHearts() {
        life--;
        switch (life) {
            case 0:
                life1.setVisibility(View.GONE);
                life3.setVisibility(View.GONE);
                life2.setVisibility(View.GONE);
                Toast.makeText(QuestionActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                endGame();
            case 1:
                life3.setVisibility(View.GONE);
                life2.setVisibility(View.GONE);
                break;
            case 2:
                life3.setVisibility(View.GONE);
                break;
        }
    }

    void startTimer() {
        cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textTime.setText(millisUntilFinished / 1000 + "");
            }
            public void onFinish() {
                setAnswer("Wrong, try again!", score);
                setHearts();
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    public void endGame() {
        cancelTimer();
        Intent intent = new Intent(getApplicationContext(), HighScore.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }

    public void confirm(View view) {
        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
            RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
            String ans = rbSelected.getText().toString();
            if (ans.equals(answer)) {
                setAnswer("Correct", (score + 10));
            } else {
                setHearts();
                setAnswer("Wrong, try again!", score);
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDB(){


        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
    }
}


