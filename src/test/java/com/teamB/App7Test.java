package com.teamB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class App7Test {
    @Test
    public void shouldAnswerWithTrue(){
        String USA = "USA";
        String JPN = "JPN";
        String EU = "EU";


        String CA = "CA";
        String SA = "SA";
        String AFICA = "AFRICA";


        Graph myGame = new Graph();
        myGame.createInitialMap(6);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");
        myGame.bindCountriesToNumbers("EU");


        myGame.bindCountriesToNumbers("CA");
        myGame.bindCountriesToNumbers("SA");
        myGame.bindCountriesToNumbers("AFRICA");

        Map myGameMap = new Map(myGame);

        Player mike = new Player("Mike", 1);

        myGameMap.addArmy(USA, mike);
        myGameMap.addArmy(AFICA, mike);
        myGameMap.addArmy(EU, mike);
        myGameMap.addArmy(JPN, mike);
        myGameMap.addArmy(CA, mike);
        myGameMap.addArmy(SA, mike);

        Twitter twitter = TwitterFactory.getSingleton();
        int countrySize = myGameMap.getCountryCount();
        String tweet = " ";
        //Status status;
        for(int i = 0; i < countrySize; i++) {
            tweet = tweet + myGameMap.getIndividualMap(i) + " ";
        }
        try {
            Status status = twitter.updateStatus(tweet);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            assertEquals(true,tweet.compareTo(status.getText()) == 0);
        }
        catch(TwitterException e) {

        }
        String test = "USA is controlled by Mike and has: 1 army(s) JPN is controlled by Mike and has: 1 army(s) EU is controlled by Mike and has: 1 army(s) CA is controlled by Mike and has: 1 army(s) SA is controlled by Mike and has: 1 army(s) AFRICA is controlled by Mike and has: 1 army(s)";
    }
}
