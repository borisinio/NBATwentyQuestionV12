package com.example.johnraviv.twentyquestionv12.classes;


import com.example.johnraviv.twentyquestionv12.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MediumQuestion implements Questions {

    private String[] questions = new String[20];
    private String[] answers = new String[60];


    private String options[][] = new String[20][4];

    public String getQuestions(int val) { return questions[val]; }

    public String getAnswers(int val) { return answers[val]; }

    public String getOptionsOne(int val) { return options[val][0]; }

    public String getOptionsTwo(int val) { return options[val][1]; }

    public String getOptionsThree(int val) { return options[val][2]; }

    public String getOptionsFour(int val) { return options[val][3]; }

    public MediumQuestion(ArrayList<Player> players) {

        for (int i =20, j=0;i<40;i++, j++){

            questions[j] = players.get(i).Adress;
            answers[j] = players.get(i).Name;
        }

        ArrayList<Integer> random = new ArrayList<>();
        random.add(0);
        random.add(1);
        random.add(2);
        random.add(3);
        Collections.shuffle(random);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    options[i][random.get(j)] = getAnswers(i);
                } else {
                    options[i][random.get(j)] = getAnswers(new Random().nextInt(20));
                }
            }
            Collections.shuffle(random);
        }

    }

}
