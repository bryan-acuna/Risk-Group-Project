package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class App8Test {

    @Test
    public void shouldAnswerWithTrue(){
        Player mike = new Player("Mike", 0);
        mike.buyCredits(mike, 100);
        mike.buyUndo();
        //Players by default get one free undo
        assertTrue(mike.getNumberOfUndos() == 2);
    }

    @Test
    public void testCardConstr(){
        String armyPicture = "INFANTRY";
        String countryPicture = "USA";
        Card myCard = new Card(armyPicture, countryPicture);
        assertTrue(myCard.getArmyPicture().compareTo("INFANTRY")==0);
    }

    @Test
    public void testCardConstr2(){
        String armyPicture = "INFANTRY";
        String countryPicture = "USA";
        Card myCard = new Card(armyPicture, countryPicture);
        assertTrue(myCard.getCountryPicture().compareTo("USA")==0);
    }
}
