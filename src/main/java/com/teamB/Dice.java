package com.teamB;
import java.util.Random;

public class Dice {
    private Random myDie;

    Dice(){
        myDie = new Random();
    }
    public int rollDice(){
        return myDie.nextInt((5)+1)+1;
        //((max - min) + 1) + min
    }
}