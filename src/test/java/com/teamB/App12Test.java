package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class App12Test {

    @Test
    public void shouldAnswerWithTrue(){
        Dice myDie = new Dice();
        assertTrue(myDie.rollDice() <=6 );
    }
    @Test
    public void mapTest1(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        assertTrue(myGameMap.getMap().size() == 2);
    }
}
