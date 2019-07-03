package com.example.johnraviv.twentyquestionv12.services;


//USERS NAMES AND SCORES - SAVED ON A LOCAL DB
public class User {
    String name;
    int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}

