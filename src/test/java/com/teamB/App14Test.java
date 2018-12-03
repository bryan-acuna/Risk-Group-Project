package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;

public class App14Test {

    @Test
    public void PlayerTest(){
        Player mike = new Player("Mike", 0);
        Deck myDeck = new Deck();
        myDeck.drawCardFromDeck(mike);

        assertTrue(mike.getCardsInHand().get(0).getArmyPicture().compareTo("INFANTRY")==0);
    }
    @Test
    public void PlayerTest1(){
        Player mike = new Player("Mike", 0);
        Deck myDeck = new Deck();
        myDeck.drawCardFromDeck(mike);

        assertTrue(mike.getCardsInHand().get(0).getCountryPicture().compareTo("USA")==0);
    }
    @Test
    public void PlayerTest2(){
        Player mike = new Player("Mike", 0);
        Deck myDeck = new Deck();
        myDeck.drawCardFromDeck(mike);

        assertTrue(mike.getCardsInHand().get(0).getArmyPicture().compareTo("INFANTRY")==0);
    }

    @Test
    public void PlayerTest3() {
        Player mike = new Player("Mike", 0);
        Deck myDeck = new Deck();
        myDeck.drawCardFromDeck(mike);
        myDeck.undoDrawCardFromDeck(mike);

        assertTrue(mike.getCardsInHand().size() == 0);
    }
    @Test
    public void PlayerTest4() {
        Player mike = new Player("Mike", 0);
        mike.addCredits(50);

        assertTrue(mike.getCredits() == 50);
    }

    @Test
    public void PlayerTest5() {
        Player mike = new Player("Mike", 0);
        mike.addCredits(50);
        mike.removeCredits(47);

        assertTrue(mike.getCredits() == 3);
    }

    @Test
    public void PlayerTest6() {
        Player mike = new Player("Mike", 0);
        mike.setPlayerName("MIKE");

        assertTrue(mike.getPlayerName().compareTo("MIKE")==0);
    }
    @Test
    public void PlayerTest7() {
        Player mike = new Player("Mike", 0);
        mike.setPlayerID(1);

        assertTrue(mike.getPlayerID() == 1);
    }
    @Test
    public void PlayerTest8() {
        Player mike = new Player("Mike", 0);
        mike.addArmiesToPlace(5);
        assertTrue(mike.getArmiesToPlace() ==5);
    }

    @Test
    public void PlayerTest9() {
        Player mike = new Player("Mike", 0);
        Player bryan = new Player("Bryan", 1);
        Graph myGraph = new Graph();
        myGraph.createInitialMap(2);
        myGraph.bindCountriesToNumbers("USA");
        myGraph.bindCountriesToNumbers("EU");

        myGraph.addEdge("USA","EU");

        Map gameMap = new Map(myGraph);
        gameMap.addArmy("USA", mike);
        gameMap.addArmy("EU", bryan);
        Dice theDie = new Dice();
        String whoWon = "";
        mike.attack(gameMap, myGraph, theDie,
                    mike, "USA", 1,
                    bryan, "EU", whoWon);

        String[] controllingPlayersBefore = new String[2];
        controllingPlayersBefore[0] = "Mike";
        controllingPlayersBefore[1] = "Bryan";
        assertTrue(Arrays.equals(controllingPlayersBefore, mike.getBeforeAttackControllingPlayers()));
    }
    @Test
    public void PlayerTest10() {
        Player mike = new Player("Mike", 0);
        Player bryan = new Player("Bryan", 1);
        Graph myGraph = new Graph();
        myGraph.createInitialMap(2);
        myGraph.bindCountriesToNumbers("USA");
        myGraph.bindCountriesToNumbers("EU");

        myGraph.addEdge("USA","EU");

        Map gameMap = new Map(myGraph);
        gameMap.addArmy("USA", mike);
        gameMap.addArmy("EU", bryan);
        Dice theDie = new Dice();
        String whoWon = "";
        mike.attack(gameMap, myGraph, theDie,
                mike, "USA", 1,
                bryan, "EU", whoWon);

        int[] numArmies = new int[2];
        numArmies[0] = 1;
        numArmies[1] = 1;
        assertTrue(Arrays.equals(numArmies, mike.getBeforeAttackNumberArmies()));
    }
















}
