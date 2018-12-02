package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class App10Test {

    @Test
    public void shouldAnswerWithTrue(){
        Player mike = new Player("Mike", 0);
        mike.buyCredits(mike, 100);
        assertTrue(mike.getCredits() == 100);
    }
    @Test
    public void testDeck(){
        Deck myDeck = new Deck();
        Player mike = new Player("Mike", 0);
        myDeck.drawCardFromDeck(mike);
        assertTrue(mike.getCardsInHand().size() == 1);
    }
    @Test
    public void testDeck2(){
        Deck myDeck = new Deck();
        Player mike = new Player("Mike", 0);
        myDeck.drawCardFromDeck(mike);
        assertTrue(mike.getCardsInHand().get(0).getArmyPicture().compareTo("INFANTRY") ==0);
    }
    @Test
    public void testDeck3(){
        Deck myDeck = new Deck();
        Player mike = new Player("Mike", 0);
        myDeck.drawCardFromDeck(mike);
        assertTrue(mike.getCardsInHand().get(0).getCountryPicture().compareTo("USA") ==0);
    }
    @Test
    public void testDeck4(){
        Deck myDeck = new Deck();
        Player mike = new Player("Mike", 0);
        myDeck.drawCardFromDeck(mike);
        myDeck.undoDrawCardFromDeck(mike);
        assertTrue(mike.getCardsInHand().size() ==0);
    }


}
