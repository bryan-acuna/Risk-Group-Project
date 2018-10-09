package com.teamB;

import java.util.*;

public class Player_Controller {
    private List<Player> playerList;
    private Map gameMap;


    Player_Controller(int playerNum, Map gameMap){
        playerList = new LinkedList();
        this.gameMap = gameMap;
        for(int i =0; i < playerNum; i++){
            System.out.println("Who is player " + i);
            Scanner sc = new Scanner(System.in);
            String playerName = sc.nextLine();
            createPlayer(playerName, i);

        }
    }

    public void fillMap(int numCountries){


        for(int i =0; i < numCountries; i++){
            int playerID = i% playerList.size();
            System.out.println("Choose a country to add an army to " + (playerList.get(playerID)).getPlayerName());

            boolean correctCountryName = true;
            String countryToClaim = "";
            while(correctCountryName) {
                Scanner sc = new Scanner(System.in);
                countryToClaim = (sc.nextLine()).toUpperCase();
                if( (gameMap.getCountriesInOrder()).contains(countryToClaim) ){
                    correctCountryName = false;
                }
                else{
                    System.out.println("Country does not exist");
                }
            }


            Player currentPlayer = playerList.get(playerID);
            gameMap.addArmy(countryToClaim, currentPlayer );

        }


    }


    public void createPlayer(String name, int playerNumber){
        Player newPlayer = new Player(name, playerNumber);
        playerList.add(newPlayer);
    }

    public List getPlayerList(){
        return playerList;
    }


    public static void main(String []args){
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
        Player_Controller gameController = new Player_Controller(2, myGameMap);
        //Show who the layers are
        for(int i =0; i < 2; i++){
            List<Player> test = gameController.getPlayerList();

            System.out.println("Player " + i+ " is " + (test.get(i)).getPlayerName());
        }

        gameController.fillMap(6);
        myGameMap.getMapStatus();

    }



}
