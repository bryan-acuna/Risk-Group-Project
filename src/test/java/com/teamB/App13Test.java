package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class App13Test {

    @Test
    public void shouldAnswerWithTrue(){
        Graph myGame = new Graph();
        myGame.createInitialMap(1);
        myGame.bindCountriesToNumbers("USA");

        assertTrue(myGame.getCountriesInOrder().get(0).compareTo("USA") ==0);
    }
    @Test
    public void testArmyConstruct(){
        Army myArmy = new Army();
        assertTrue(myArmy.getControllingPlayer().compareTo("None") == 0);
    }

    @Test
    public void testArmyNumber(){
        Army myArmy = new Army();
        assertTrue(myArmy.getNumberArmies() == 0);
    }
    @Test
    public void testAddArmy(){
        Army myArmy = new Army();
        myArmy.addArmy();
        assertTrue(myArmy.getNumberArmies() == 1);
    }
    @Test
    public void testSubArmy(){
        Army myArmy = new Army();
        myArmy.addArmy();
        myArmy.subArmy();
        assertTrue(myArmy.getNumberArmies() == 0);
    }
}
