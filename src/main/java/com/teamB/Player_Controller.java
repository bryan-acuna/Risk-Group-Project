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
    /*
     * Gives each player a number of armies based on number of players
     *
     * returns the number given to each player
     * */
    public int givePlayersStartingArmies(){
        int numberPlayers = playerList.size();
        int numArmiesEach = 0;
        //just for testing easier
        if(numberPlayers == 2){
            numArmiesEach = 3;
        }
        else if(numberPlayers == 3){
            numArmiesEach = 35;
        }
        else if(numberPlayers == 4){
            numArmiesEach = 30;
        }
        else if(numberPlayers == 5){
            numArmiesEach = 25;
        }
        else{
            numArmiesEach = 20;
        }
        for(int i =0; i < playerList.size(); i++){
            (playerList.get(i)).addArmiesToPlace(numArmiesEach);
        }
        return numArmiesEach;
    }


    public void fillMap(){
        int numberArmiesEach = givePlayersStartingArmies();
        int armiesToPlace = numberArmiesEach*playerList.size();

        for(int i =0; i < armiesToPlace; i++){
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
            boolean countryAdded = false;
            while(countryAdded == false) {
                if (gameMap.addArmy(countryToClaim, currentPlayer)) {
                    countryAdded = true;
                }
                else{
                    gameMap.addArmy(countryToClaim, currentPlayer);
                    System.out.println("Please choose another country to claim");
                    Scanner sc = new Scanner(System.in);
                    countryToClaim = (sc.nextLine()).toUpperCase();
                }
            }

            System.out.println("Do you want to undo your action? (y/n)");
            String undoAnswer = "";
            Scanner sc = new Scanner(System.in);
            undoAnswer = (sc.nextLine()).toUpperCase();
            //System.out.println("value of undoAnswer" +undoAnswer);
            if(undoAnswer.equals("Y")){
                i--;
                gameMap.setCountriesAvailable(gameMap.getCountriesAvailable()+1);

                //Idea is to use the player input of which country they selected
                //find the army object inside the country
                //Then see if it is just one 1 army, in which case the undo will remove
                //ownership and revert the country back to no one owns it
                //Or if there are more than 1 army, just remove one army from the country
                HashMap<String, Integer> countryIDFromString = gameMap.getHashMap();
                int countryID = countryIDFromString.get(countryToClaim);
                List<Army> countries = gameMap.getMap();


                Army armiesInCountry = countries.get(countryID);

                //System.out.println("here is the country they are trying to undo:" +countryToClaim);
                //armiesInCountry.print();

                if(armiesInCountry.getNumberArmies() == 1){
                    armiesInCountry.setControllingPlayer("None");
                    armiesInCountry.setNumberArmies(0);
                }
                else{
                    armiesInCountry.subArmy();
                }

            }
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


        //myGame.bindCountriesToNumbers("CA");
        //myGame.bindCountriesToNumbers("SA");
        //myGame.bindCountriesToNumbers("AFRICA");


        //myGame.addEdge(USA, CA);
        //myGame.addEdge(USA, SA);
        //myGame.addEdge(EU, AFICA);


        myGame.printMapAdjacencies();




        Map myGameMap = new Map(myGame);
        Player_Controller gameController = new Player_Controller(2, myGameMap);
        //Show who the layers are
        for(int i =0; i < gameController.playerList.size(); i++){
            List<Player> test = gameController.getPlayerList();
            System.out.println("Player " + i+ " is " + (test.get(i)).getPlayerName());
        }

        //allows players to claim countries and add initial armies to them
        gameController.fillMap();

        //prints status of map so far
        myGameMap.getMapStatus();

    }



}