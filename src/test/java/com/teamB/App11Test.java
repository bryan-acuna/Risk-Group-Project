package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class App11Test {

    @Test
    public void shouldAnswerWithTrue(){
        Player mike = new Player("Mike", 0);

        assertTrue(mike.getPlayerName().compareTo("Mike")==0);
    }
    @Test
    public void graphTest1(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        assertTrue(myGame.getCountriesInOrder().size() ==2);
    }
    @Test
    public void graphTest2(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        assertTrue(myGame.getCountryCounter() ==2);
    }
    @Test
    public void graphTest3(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        assertTrue(myGame.getCountryAdjacency("USA").size() == 1);
    }
    @Test
    public void graphTest4(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        assertTrue(myGame.isCountryAdjacent("USA", "JPN"));
    }


}
