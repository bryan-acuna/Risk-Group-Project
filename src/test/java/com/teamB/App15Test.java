package com.teamB;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.teamB.Dice;
import org.junit.Test;

public class App15Test {
    @Test
    public void shouldAnswerWithTrue()
    {
        String USA = "USA";
        String JPN = "JPN";
        String EU = "EU";

        Graph myGame = new Graph();
        myGame.createInitialMap(3);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");
        myGame.bindCountriesToNumbers("EU");

        myGame.addEdge(USA, JPN);
        myGame.addEdge(USA, EU);
        myGame.addEdge(EU, JPN);

        Map myGameMap = new Map(myGame);
        Deck myDeck = new Deck();
        Player_Controller player_controller = new Player_Controller(10,myGameMap,myDeck ,myGame )
        player_controller.createPlayer("Jason",4);
        assertTrue((player_controller.getPlayerList().size() == 1));
    }
}
