package com.teamB;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws Exception
    {
        //Creates the graph we can reference for adjacency
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


        myGame.addEdge(USA, CA);
        myGame.addEdge(USA, SA);
        myGame.addEdge(EU, AFICA);


        myGame.printMapAdjacencies();


//            System.out.println("Adjacent Countries: " + myGame.getCountryAdjacency("USA"));
//            System.out.println("");


        Map myGameMap = new Map(myGame);
        Deck theDeck = new Deck();


        Player mike = new Player("Mike", 1);
        Player bryan = new Player("Bryan", 2);
        Player brandon = new Player("Brandon", 3);
        Player_Controller gameController = new Player_Controller(2, myGameMap, theDeck, myGame);

        myGameMap.addArmy(USA, mike);
        myGameMap.addArmy(AFICA, mike);
        myGameMap.addArmy(EU, mike);
        myGameMap.addArmy(JPN, mike);
        myGameMap.addArmy(CA, mike);
        myGameMap.addArmy(SA, mike);

        myGameMap.getMapStatus();



        Twitter twitter = TwitterFactory.getSingleton();
    // System.out.println("Type in tweet message: ");
    // Scanner scan = new Scanner(System.in);
    // String tweet = scan.nextLine();
        int countrySize = myGameMap.getCountryCount();
        String tweet = " ";
        for(int i = 0; i < countrySize; i++) {
            tweet = tweet + myGameMap.getIndividualMap(i) + " ";
        }
        Status status = twitter.updateStatus(tweet);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");

        if(myGameMap.isGameOver()) {
            String finalTweet = " ";
            for(int i = 0; i < countrySize; i++) {
                finalTweet = finalTweet + myGameMap.getIndividualMap(i) + " ";
            }
            Status finalStatus = twitter.updateStatus("End score:" + tweet);
            System.out.println("Successfully updated the status to [" + finalStatus.getText() + "].");
        }
    }
}