package com.teamB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;
import java.util.*;

public class Player_Controller {
    private List<Player> playerList;
    private Map gameMap;
    private Deck theDeck;
    private Graph theGraph;


    Player_Controller(int playerNum, Map gameMap, Deck theDeck, Graph theGraph){
        playerList = new LinkedList();
        this.gameMap = gameMap;
        this.theDeck = theDeck;
        this.theGraph = theGraph;
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
            numArmiesEach = 1;
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
        List<Army> countriesState = new ArrayList<>();

        //For intial claiming countries, just let them undo for free
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
            //System.out.println("Value of credits =" + playerList.get(playerID).getCredits() );
            //if(playerList.get(playerID).getCredits() > 0 ) {
                if (undoAnswer.equals("Y")) {
                    i--;
                    System.out.println("Free undo since start of game");
                    gameMap.setCountriesAvailable(gameMap.getCountriesAvailable() + 1);

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

                    if (armiesInCountry.getNumberArmies() == 1) {
                        armiesInCountry.setControllingPlayer("None");
                        armiesInCountry.setNumberArmies(0);
                    } else {
                        armiesInCountry.subArmy();
                    }

                }
            //}
            //else{
                //System.out.println("Insufficient credits for undo, moving to next player");
            //}
        }// for loop end

        int playerTurn =0;
        int amountToBuy = 0;
        int selection = 0;
        String receiverName = "";
        int creditsToTransfer = 0;

        Dice theDice = new Dice();
        while(!gameMap.isGameOver()){
            int playerID = playerTurn% playerList.size();
            Player currentPlayer =  playerList.get(playerID);
            playerTurn++;

            System.out.println("What would you like to do?" + currentPlayer.getPlayerName() +
                    "Enter 0 for attack, 1 for trade in cards," +
                    "2 for buying credits, 3 for transfer credits"+
                    ",4 to skip turn, 5 to spend credits");
            Scanner sc = new Scanner(System.in);
            int answer  = sc.nextInt();
            if(answer == 0){
                String whoWon = "";
                currentPlayer.attack(gameMap, theGraph, theDice, currentPlayer, playerList, whoWon);
                //give card if attack is success in currentPlayer favor
                if(whoWon == "ATTACKER"){
                    theDeck.drawCardFromDeck(currentPlayer);
                }
                System.out.println("here is the status of the map");
                gameMap.getMapStatus();
            }


            else if(answer == 1){
                System.out.println("Here are the number of cards before traded in: ");
                currentPlayer.printCardsInHard();
                currentPlayer.tradeInCards();
                System.out.println("Here are the number of cards after traded in: ");
                currentPlayer.printCardsInHard();
            }


            else if(answer == 2){
                System.out.println("how many credits would you like to buy?");
                Scanner sc1 = new Scanner(System.in);
                amountToBuy = sc1.nextInt();
                System.out.println("Here are number of credits before buy" + currentPlayer.getCredits());

                currentPlayer.buyCredits(currentPlayer, amountToBuy);
                System.out.println("Here are number of credits after buy" + currentPlayer.getCredits());
            }
            else if(answer == 3){

                System.out.println("Enter player name to transfer to");
                Scanner sc1 = new Scanner(System.in);
                receiverName = sc1.nextLine();
                System.out.println("How many credits to transfer?");
                Scanner sc2 = new Scanner(System.in);
                creditsToTransfer= sc2.nextInt();

                Player receiver = new Player();
                for(int i =0; i < playerList.size(); i++){
                    if(receiverName == playerList.get(i).getPlayerName()){
                        receiver = playerList.get(i);
                    }
                }
                System.out.println("Here are number of credits of player transfering before: " + currentPlayer.getCredits());
                System.out.println("Here are number of credits of receiver before: " + receiver.getCredits());
                currentPlayer.transferCredits(currentPlayer, receiver, creditsToTransfer);
                System.out.println("Here are number of credits of player transfering after: " + currentPlayer.getCredits());
                System.out.println("Here are number of credits of receiver after: " + receiver.getCredits());

            }
            else if(answer == 4){
                //Does nothing because they are skipping turn
            }
            else if(answer == 5){
                System.out.println("What would you like to buy?" +
                        "Enter 0 for buy undo (cost 1),Enter 1 for buy card (cost 2) ");
                Scanner sc1 = new Scanner(System.in);
                selection = sc1.nextInt();
                if(selection == 0 && currentPlayer.getCredits() >= 1){
                    System.out.println("number of credits before buy: " + currentPlayer.getCredits()
                            + " and number of undos: "+ currentPlayer.getNumberOfUndos());
                    currentPlayer.removeCredits(1);
                    currentPlayer.buyUndo();
                    System.out.println("number of credits after buy: " + currentPlayer.getCredits()
                            + " and number of undos: "+ currentPlayer.getNumberOfUndos());
                }
                else if(selection == 1 && currentPlayer.getCredits() >=2){
                    System.out.println("number of credits before buy: " + currentPlayer.getCredits()
                            + " and cards in hand");
                    currentPlayer.printCardsInHard();
                    currentPlayer.removeCredits(1);
                    theDeck.drawCardFromDeck(currentPlayer);

                    System.out.println("number of credits after buy: " + currentPlayer.getCredits()
                            + " and cards in hand");
                    currentPlayer.printCardsInHard();
                }
            }
            System.out.println("Do you wish to undo action?(y/n");
            Scanner scAnswer = new Scanner(System.in);
            String undoAnswer  = scAnswer.nextLine().toUpperCase();
            if(undoAnswer == "Y") {
                System.out.println("Here are current player Number of undos" + currentPlayer.getNumberOfUndos());
                if (currentPlayer.getNumberOfUndos() > 0) {


                    List<Army> countryStates = currentPlayer.getCountryStates();
                    int[] getAttackerAndDefender = currentPlayer.getAttackerAndDefender();
                    if (answer == 0) {
                        System.out.println("undoing the attack");
                        System.out.println("value of the countries after the attack: ");
                        gameMap.getMapStatus();

                        gameMap.getMap().get(getAttackerAndDefender[0]).setControllingPlayer(countryStates.get(0).getControllingPlayer());
                        gameMap.getMap().get(getAttackerAndDefender[0]).setNumberArmies(countryStates.get(0).getNumberArmies());

                        gameMap.getMap().get(getAttackerAndDefender[1]).setControllingPlayer(countryStates.get(1).getControllingPlayer());
                        gameMap.getMap().get(getAttackerAndDefender[1]).setNumberArmies(countryStates.get(1).getNumberArmies());

                        System.out.println("value of the countries after the undo: ");
                        gameMap.getMapStatus();

                        //undo attack
                        //currentPlayer.getUndo minus 1
                    }
                    else if(answer ==1){
                        //undo traded in cards
                        //currentPlayer.useUndo()
                    }
                    else if (answer == 2) {
                        System.out.println("value of credits after buy: " + currentPlayer.getCredits());
                        currentPlayer.removeCredits(amountToBuy);
                        currentPlayer.useUndo();
                        System.out.println("value of credits after undo: "+ currentPlayer.getCredits());
                    } else if (answer == 3) {
                        Player receiver = new Player();
                        for (int i = 0; i < playerList.size(); i++) {
                            if (receiverName == playerList.get(i).getPlayerName()) {
                                receiver = playerList.get(i);
                            }
                        }
                        System.out.println("Here are number of credits of player transfering before: " + currentPlayer.getCredits());
                        System.out.println("Here are number of credits of receiver before: " + receiver.getCredits());


                        currentPlayer.transferCredits(receiver, currentPlayer, creditsToTransfer);


                        System.out.println("Here are number of credits of player transfering after: " + currentPlayer.getCredits());
                        System.out.println("Here are number of credits of receiver after: " + receiver.getCredits());

                        currentPlayer.useUndo();
                    }
                    else if (answer == 4) {
                        //skip turn undo soon

                    }
                    else if (answer == 5) {
                        if (selection == 0) {
                            System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                            System.out.println("Here are number of undos of player after buying: " + currentPlayer.getNumberOfUndos());
                            currentPlayer.addCredits(1);
                            //once to remove the bought undo, and second to actually subtract the undo used
                            currentPlayer.useUndo();
                            currentPlayer.useUndo();
                            System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                            System.out.println("Here are number of undos of player after buying: " + currentPlayer.getNumberOfUndos());
                        }
                        else if (selection == 1) {
                            System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                            System.out.println("Here are cards in hand after buying: ");
                            currentPlayer.printCardsInHard();

                            currentPlayer.addCredits(1);
                            theDeck.undoDrawCardFromDeck(currentPlayer);
                            currentPlayer.useUndo();

                            System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                            System.out.println("Here are cards in hand after buying: ");
                            currentPlayer.printCardsInHard();

                        }

                    }
                }
                else if (currentPlayer.getNumberOfUndos() == 0) {
                    System.out.println("Sorry no undo's left");
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
        //uncomment when done testing
//        String key ="";
//        String secKey = "";
//
//        Properties prop = new Properties();
//        InputStream input = null;
//        try {
//            input = new FileInputStream("config.properties");
//
//            // load a properties file
//            prop.load(input);
//
//            // get the property value and print it out
//            key = prop.getProperty("key");
//            secKey = prop.getProperty("secKey");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        UploadObject myUpload = new UploadObject();
//        String filePath = "fileToS3";
//        myUpload.upload(key, secKey,"mikebitest05012018", "gameStatus",  filePath);
//
        String USA = "USA";
        String JPN = "JPN";
        String EU = "EU";
//        String CA = "CA";
//        String SA = "SA";
//        String AFRICA = "AFRICA";


        Graph myGame = new Graph();
        myGame.createInitialMap(3);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");
        myGame.bindCountriesToNumbers("EU");
//        myGame.bindCountriesToNumbers("CA");
//        myGame.bindCountriesToNumbers("SA");
//        myGame.bindCountriesToNumbers("AFRICA");
//        myGame.addEdge(USA, CA);
//        myGame.addEdge(USA, SA);
//        myGame.addEdge(EU, AFRICA);
        myGame.addEdge(USA, JPN);



        myGame.printMapAdjacencies();
        Deck theDeck = new Deck();
//
//
        Map myGameMap = new Map(myGame);
        Dice theDie = new Dice();
//
//        Player bryan = new Player("Bryan", 0);
//        Player jake = new Player("Jake", 1);
//        myGameMap.addArmy(USA, bryan);
//        myGameMap.addArmy(EU, bryan);
//        myGameMap.addArmy(JPN, jake);
//        myGameMap.addArmy(USA, bryan);
//        myGameMap.getMapStatus();
//        bryan.attack(myGameMap, myGame, theDie, bryan, jake);
//        System.out.println();
//        myGameMap.getMapStatus();
//
//
//
//
        Player_Controller gameController = new Player_Controller(2, myGameMap, theDeck, myGame);
        //Show who the layers are
        for(int i =0; i < gameController.playerList.size(); i++){
            List<Player> test = gameController.getPlayerList();
            System.out.println("Player " + i+ " is " + (test.get(i)).getPlayerName());
        }

        //allows players to claim countries and add initial armies to them
        gameController.fillMap();

        //prints status of map so far
        myGameMap.getMapStatus();
//        Player_Controller gameController = new Player_Controller(2, myGameMap);
//


    }



}