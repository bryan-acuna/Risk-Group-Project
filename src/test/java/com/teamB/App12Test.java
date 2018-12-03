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
    @Test
    public void mapTest2(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        assertTrue(myGameMap.getCountriesInOrder().size() == 2);
    }

    @Test
    public void mapTest3(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        assertTrue(myGameMap.getCountriesInOrder() == myGame.getCountriesInOrder());
    }
    @Test
    public void mapTest4(){
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        assertTrue(myGameMap.getCountryCount() == 2);
    }

    @Test
    public void mapTest5(){
        Player mike = new Player("Mike", 0);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        assertTrue(myGameMap.getMap().get(0).getControllingPlayer().compareTo("Mike")==0);
    }
    @Test
    public void mapTest6(){
        Player mike = new Player("Mike", 0);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        assertTrue(myGameMap.getMap().get(0).getNumberArmies()==1);
    }
    @Test
    public void mapTest7(){
        Player mike = new Player("Mike", 0);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        myGameMap.removeOneArmy("USA");
        assertTrue(myGameMap.getMap().get(0).getControllingPlayer().compareTo("Mike")==0);
    }
    @Test
    public void mapTest8(){
        Player mike = new Player("Mike", 0);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        myGameMap.removeOneArmy("USA");
        assertTrue(myGameMap.getMap().get(0).getNumberArmies() ==0);

    }
    @Test
    public void mapTest9(){
        Player mike = new Player("Mike", 0);
        Player bryan = new Player("Bryan", 1);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        myGameMap.TakeOver("USA", "Bryan");
        assertTrue(myGameMap.getMap().get(0).getControllingPlayer().compareTo("Bryan")==0);
    }
    @Test
    public void mapTest10(){
        Player mike = new Player("Mike", 0);
        Player bryan = new Player("Bryan", 1);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        myGameMap.TakeOver("USA", "Bryan");
        assertTrue(myGameMap.getMap().get(0).getControllingPlayer().compareTo("Bryan")==0);
    }
    @Test
    public void mapTest11(){
        Player mike = new Player("Mike", 0);
        Graph myGame = new Graph();
        myGame.createInitialMap(2);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");

        myGame.addEdge("USA", "JPN");

        Map myGameMap = new Map(myGame);
        myGameMap.addArmy("USA", mike);
        myGameMap.addArmy("JPN", mike);
        assertTrue(myGameMap.isGameOverCheck());
    }





}
