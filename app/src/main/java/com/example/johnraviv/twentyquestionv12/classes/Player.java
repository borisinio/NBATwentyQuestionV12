package com.example.johnraviv.twentyquestionv12.classes;


//CLASS FOR THE NBA PLAYERS
public class Player {
    String Adress;
    String Name;

    public Player(){}

    @Override
    public String toString() {
        return "Player{" +
                "Adress='" + Adress + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
