package com.teamB;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
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



        //System.out.println(myGameMap.isGameOver());




        


            /*
            List<String> adjacencyOfUSA = myGame.getCountryAdjacency(USA);
            if(adjacencyOfUSA.contains(CA)) {
                System.out.println("Yes");
            }
            List<String> adjacencyOfUSA2 = myGame.getCountryAdjacency(USA);
                if(adjacencyOfUSA2.contains(AFICA)){
                    System.out.println("Yes");
                }
                else{
                    System.out.println("No");
                }
            List<String> adjacencyOfJPN = myGame.getCountryAdjacency(JPN);
            if(adjacencyOfJPN.isEmpty()){
                System.out.println("empty");
            }
            for(String adjacency: adjacencyOfJPN){
                if(CA == adjacency){
                    System.out.println("Yes");
                }
                else{
                    System.out.println("No");
                }
            }
            */


    }
}