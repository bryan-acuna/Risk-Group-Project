package com.teamB;



import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import java.util.*;


public class Player_Controller {
    private List<Player> playerList;
    private Map gameMap;
    private Deck theDeck;
    private Graph theGraph;
    static private int gameCounter =0;
    private String gameID;


    //Variables used for file reader
    private File file; //this is hard coded "fileToS3", but can change later on
    private long lastModified;
    private int currentLineInFile;



    private chatBot bot;


    Player_Controller(int playerNum, Map gameMap, Deck theDeck, Graph theGraph){

        playerList = new LinkedList();
        this.gameMap = gameMap;
        this.theDeck = theDeck;
        this.theGraph = theGraph;
        this.gameID = "123";

        //Setup for bot
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        bot = new chatBot();

        try{

            telegramBotsApi.registerBot(bot);
        }
        catch(TelegramApiRequestException e){
            e.printStackTrace();
        }

        //dont really need this yet, later on
        //this.gameID = gameCounter;
        //gameCounter++;


        //setupFile and initialize lastMod
        file = new File("fileToS3");
        //clearFile
        clearFile(file);


        lastModified= file.lastModified();
        currentLineInFile = 1;
        waitForPlayers();
        for(int i =0; i < playerNum; i++){
            //System.out.println("Who is player " + i);
            bot.sendMessage("Who is player "+ i);
            //Scanner sc = new Scanner(System.in);
            String playerName = fileCheck(file, lastModified, currentLineInFile);
            currentLineInFile++;
            lastModified = file.lastModified();
            createPlayer(playerName, i);
        }
    }

    public String fileCheck(File file, long lastModified, int lineToRead) {
        file = new File("fileToS3");

        //long lastMod = file.lastModified();
        long lastMod = lastModified;
        long t = System.currentTimeMillis();
        long end = t + 30000;
        boolean timeExceeded = false;
        while(System.currentTimeMillis() < end && lastMod == file.lastModified()) {

        }

        if(t >= end){
            timeExceeded = true;
        }
        if(lastMod == file.lastModified()){
            System.out.println("no answer");
            try {
                File fileToWrite = new File("fileToS3");
                FileWriter fr = new FileWriter(fileToWrite, true);
                fr.write("\n"+ "4");
                fr.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else {

            try {
                if(timeExceeded == false) {
                    String lineData = Files.readAllLines(Paths.get("fileToS3"), Charset.defaultCharset()).get(lineToRead);
                    return lineData;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "4";
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
            numArmiesEach = 2;
        }
        else if(numberPlayers == 3){
            numArmiesEach = 2;
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


    public void undoAddArmyToCountry(String countryName, Player thePlayer) {
        if(thePlayer.getNumberOfUndos() < 1){
            System.out.println("Not enough credits for undo");
        }
        else{
            String capitalCountryName = countryName.toUpperCase();
            gameMap.setCountriesAvailable(gameMap.getCountriesAvailable()+1);
            //Idea is to use the player input of which country they selected
            //find the army object inside the country
            //Then see if it is just one 1 army, in which case the undo will remove
            //ownership and revert the country back to no one owns it
            //Or if there are more than 1 army, just remove one army from the country
            HashMap<String, Integer> countryIDFromString = gameMap.getHashMap();
            int countryID = countryIDFromString.get(capitalCountryName);
            List<Army> countries = gameMap.getMap();
            Army armiesInCountry = countries.get(countryID);
            if (armiesInCountry.getNumberArmies() == 1) {
                armiesInCountry.setControllingPlayer("None");
                armiesInCountry.setNumberArmies(0);
            } else {
                armiesInCountry.subArmy();
            }
            thePlayer.useUndo();
        }
    }

    public void undoAttack(Player currentPlayer){
        if(currentPlayer.getNumberOfUndos() < 1){
            System.out.println("Not enough credits for undo");
        }
        else {
            List<Army> countryStates = currentPlayer.getCountryStates();
            int[] getAttackerAndDefender = currentPlayer.getAttackerAndDefender();

            System.out.println("undoing the attack");
            System.out.println("value of the countries after the attack: ");
            gameMap.getMapStatus();

            gameMap.getMap().get(getAttackerAndDefender[0]).setControllingPlayer(countryStates.get(0).getControllingPlayer());
            gameMap.getMap().get(getAttackerAndDefender[0]).setNumberArmies(countryStates.get(0).getNumberArmies());
            gameMap.getMap().get(getAttackerAndDefender[1]).setControllingPlayer(countryStates.get(1).getControllingPlayer());
            gameMap.getMap().get(getAttackerAndDefender[1]).setNumberArmies(countryStates.get(1).getNumberArmies());
            System.out.println("value of the countries after the undo: ");
            gameMap.getMapStatus();
            currentPlayer.useUndo();
        }
    }

    public void undoBuyCredits(Player currentPlayer, int creditsToRefund) {
        if (currentPlayer.getNumberOfUndos() < 1) {
            System.out.println("Not enough credits for undo");
        } else {
            System.out.println("value of credits after buy: " + currentPlayer.getCredits());
            currentPlayer.removeCredits(creditsToRefund);
            currentPlayer.useUndo();
            System.out.println("value of credits after undo: " + currentPlayer.getCredits());

        }
    }

    public void undoTransferCredits(Player currentPlayer, Player playerInitiallyTransferedCredits, int transferedCredits){
        if(currentPlayer.getNumberOfUndos() < 1){
            System.out.println("Not enough credits for undo");
        }
        else {currentPlayer.transferCredits(playerInitiallyTransferedCredits, currentPlayer, transferedCredits);
        currentPlayer.useUndo();}
    }

    public void undoBuyUndo(Player currentPlayer){
        if(currentPlayer.getNumberOfUndos() < 1){
            System.out.println("Not enough credits for undo");
        }
        else {currentPlayer.addCredits(1);
        currentPlayer.useUndo();
        currentPlayer.useUndo();}
    }

    public void undoBuyCard(Player currentPlayer){
        if(currentPlayer.getNumberOfUndos() < 1){
            System.out.println("Not enough credits for undo");
        }
        else {currentPlayer.addCredits(1);
        theDeck.undoDrawCardFromDeck(currentPlayer);
        currentPlayer.useUndo();}
    }

    public void printAvailableCountries(){
        System.out.println("Here are the countries that have not been claimed");
        for(int i =0; i < gameMap.getCountryCount(); i++){
            if(gameMap.getMap().get(i).getControllingPlayer() == "None"){
                System.out.println(gameMap.getCountriesInOrder().get(i));
            }
        }
    }
    public void clearFile(File file){
        try {
            String str = "NewGame:";
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(str);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void waitForPlayers(){
        int playersJoined =0;
        String enteredGameID;
        while(playersJoined < 3){
            bot.sendMessage("Enter a gameID please");
            enteredGameID = fileCheck(file, lastModified, currentLineInFile);
            currentLineInFile++;
            lastModified = file.lastModified();
            System.out.println("entered gameID" +enteredGameID);
            if(enteredGameID.compareTo(this.gameID) == 0){
                playersJoined++;
            }
            System.out.println("value of players joined" + playersJoined);
        }
    }

    public boolean checkIfValidPlayer(Player currentPlayer){
        boolean boolValidPlayer = false;
        int i = 0;
        while(boolValidPlayer == false && i < gameMap.getMap().size()){
            if(gameMap.getMap().get(i).getControllingPlayer() == currentPlayer.getPlayerName()){
                boolValidPlayer = true;
            }
            i++;
        }
        return boolValidPlayer;
    }

    public void tweetGame(){
        Twitter twitter = TwitterFactory.getSingleton();
        int countrySize = gameMap.getCountryCount();
        String tweet = " ";
        for(int i = 0; i < countrySize; i++) {
            tweet = tweet + gameMap.getIndividualMap(i) + " ";
        }
        try {
            Status status = twitter.updateStatus(tweet);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        }
        catch(TwitterException e){
            //System.out.println("No change on map");
        }
    }



    public void fillMap(){

        int numberArmiesEach = givePlayersStartingArmies();
        int armiesToPlace = numberArmiesEach*playerList.size();
        List<Army> countriesState = new ArrayList<>();

        //For intial claiming countries, just let them undo for free
        System.out.println(armiesToPlace);
        for(int i =0; i < armiesToPlace; i++){
            int playerID = i% playerList.size();
            bot.sendMessage(playerList.get(playerID).getPlayerName() +" choose a country to add an army");
            //System.out.println("Choose a country to add an army to " + (playerList.get(playerID)).getPlayerName());

            boolean correctCountryName = true;
            String countryToClaim = "";
            while(correctCountryName) {
                //Scanner sc = new Scanner(System.in);
                //countryToClaim = (sc.nextLine()).toUpperCase();

                countryToClaim = fileCheck(file, lastModified, currentLineInFile);
                currentLineInFile++;
                lastModified = file.lastModified();


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
                    //gameMap.addArmy(countryToClaim, currentPlayer);
                    System.out.println("Please choose another country to claim");
                    //Scanner sc = new Scanner(System.in);
                    //countryToClaim = (sc.nextLine()).toUpperCase();
                    countryToClaim = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();

                }
            }

        }// for loop end










        int playerTurn =0;
        int amountToBuy = 0;
        int selection = 0;
        String receiverName = "";
        int creditsToTransfer = 0;

        Dice theDice = new Dice();
        while(gameMap.isGameOverCheck() == false) {

            int playerID = playerTurn % playerList.size();
            Player currentPlayer = playerList.get(playerID);
            playerTurn++;
            if (checkIfValidPlayer(currentPlayer) == false) {
            }
            else {
                bot.sendMessage("What would you like to do?" + currentPlayer.getPlayerName() +
                        "Enter 0 for attack, 1 for trade in cards," +
                        "2 for buying credits, 3 for transfer credits" +
                        ",4 to skip turn, 5 to spend credits");

                int answer = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                currentLineInFile++;
                lastModified = file.lastModified();
                if (answer == 0) {
                    String whoWon = "";
                    //currentPlayer.attack(gameMap, theGraph, theDice, currentPlayer, playerList, whoWon);
                    //give card if attack is success in currentPlayer favor

                    bot.sendMessage("Which country would you like to attack with?");
                    String countryToAttackWith = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();

                    bot.sendMessage("How many armies?");
                    String numberOfArmies = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();

                    bot.sendMessage("Which country would you like to attack?");
                    String countryToAttack = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();

                    bot.sendMessage("Which player is it?");
                    String playerInList = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();

                    System.out.println(playerList.get(Integer.parseInt(playerInList)).getPlayerName());

                    currentPlayer.attack(gameMap, theGraph, theDice,
                            currentPlayer, countryToAttackWith, Integer.parseInt(numberOfArmies),
                            playerList.get(Integer.parseInt(playerInList)), countryToAttack, whoWon);


                    if (whoWon == "ATTACKER") {
                        theDeck.drawCardFromDeck(currentPlayer);
                    }
                    System.out.println("here is the status of the map");
                    gameMap.getMapStatus();
                } else if (answer == 1) {
                    bot.sendMessage("Here are the number of cards before traded in: ");
                    bot.sendMessage("Here are the cards in hand");
                    currentPlayer.printCardsInHard();
                    bot.sendMessage("Select first card");
                    int card1 = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();
                    bot.sendMessage("Select second card");
                    int card2 = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();
                    bot.sendMessage("Select third card");
                    int card3 = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();





                    currentPlayer.tradeInCards(card1, card2, card3);
                    bot.sendMessage("Here are the number of cards after traded in: ");
                    currentPlayer.printCardsInHard();
                } else if (answer == 2) {
                    bot.sendMessage("how many credits would you like to buy?");

                    amountToBuy = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();
                    System.out.println("Here are number of credits before buy" + currentPlayer.getCredits());

                    currentPlayer.buyCredits(currentPlayer, amountToBuy);
                    System.out.println("Here are number of credits after buy" + currentPlayer.getCredits());
                } else if (answer == 3) {

                    bot.sendMessage("Enter player name to transfer to");

                    receiverName = fileCheck(file, lastModified, currentLineInFile);
                    currentLineInFile++;
                    lastModified = file.lastModified();
                    bot.sendMessage("How many credits to transfer?");
                    creditsToTransfer = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();

                    Player receiver = new Player();
                    for (int i = 0; i < playerList.size(); i++) {
                        if (receiverName.compareTo(playerList.get(i).getPlayerName()) == 0) {
                            receiver = playerList.get(i);
                            System.out.println("The player you are transfering credits to is player number: " + i);
                        }
                    }
                    System.out.println("Here are number of credits of player transfering before: " + currentPlayer.getCredits());
                    System.out.println("Here are number of credits of receiver before: " + receiver.getCredits());
                    currentPlayer.transferCredits(currentPlayer, receiver, creditsToTransfer);
                    System.out.println("Here are number of credits of player transfering after: " + currentPlayer.getCredits());
                    System.out.println("Here are number of credits of receiver after: " + receiver.getCredits());

                } else if (answer == 4) {
                    //Does nothing because they are skipping turn
                } else if (answer == 5) {
                    bot.sendMessage("What would you like to buy?" +
                            "Enter 0 for buy undo (cost 1),Enter 1 for buy card (cost 2) ");

                    selection = Integer.parseInt(fileCheck(file, lastModified, currentLineInFile));
                    currentLineInFile++;
                    lastModified = file.lastModified();
                    if (selection == 0 && currentPlayer.getCredits() >= 1) {
                        System.out.println("number of credits before buy: " + currentPlayer.getCredits()
                                + " and number of undos: " + currentPlayer.getNumberOfUndos());
                        currentPlayer.removeCredits(1);
                        currentPlayer.buyUndo();
                        System.out.println("number of credits after buy: " + currentPlayer.getCredits()
                                + " and number of undos: " + currentPlayer.getNumberOfUndos());
                    } else if (selection == 1 && currentPlayer.getCredits() >= 2) {
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

                bot.sendMessage("Do you wish to undo action?(y/n)");
                String undoAnswer = fileCheck(file, lastModified, currentLineInFile);
                currentLineInFile++;
                lastModified = file.lastModified();

                if ((undoAnswer.toUpperCase()).compareTo("Y") == 0) {
                    System.out.println("Here are current player Number of undos" + currentPlayer.getNumberOfUndos());
                    if (currentPlayer.getNumberOfUndos() > 0) {


                        List<Army> countryStates = currentPlayer.getCountryStates();
                        int[] getAttackerAndDefender = currentPlayer.getAttackerAndDefender();

                        String []beforeAttackNames = currentPlayer.getBeforeAttackControllingPlayers();
                        int []beforeAttackNumArmies = currentPlayer.getBeforeAttackNumberArmies();
                        if (answer == 0) {
                            System.out.println("undoing the attack");
                            //Old version Doesn't work because the value passed into the arrayList that I thought
                            //Was a copy of the map before the attack, is actually a reference
                            //SO when i change the map, then the thing stored inside the arrayList countryStates
                            //of course changes aswell


                            //Works
                            System.out.println("Here are the values of the attacking country before the attack: " +
                                    "controlling player =" +  beforeAttackNames[0]+
                                    "number of armies=" + beforeAttackNumArmies[0]);
                            System.out.println("Here are the values of the defending country before the attack: " +
                                    "controlling player =" +  beforeAttackNames[1]+
                                    "number of armies=" + beforeAttackNumArmies[1]);

                            System.out.println("value of the countries before the attack: ");
                            gameMap.getMapStatus();

                            gameMap.getMap().get(getAttackerAndDefender[0]).setControllingPlayer(beforeAttackNames[0]);
                            gameMap.getMap().get(getAttackerAndDefender[0]).setNumberArmies(beforeAttackNumArmies[0]);

                            gameMap.getMap().get(getAttackerAndDefender[1]).setControllingPlayer(beforeAttackNames[1]);
                            gameMap.getMap().get(getAttackerAndDefender[1]).setNumberArmies(beforeAttackNumArmies[1]);

                            System.out.println("value of the countries after the undo: ");
                            gameMap.getMapStatus();

                            //undo attack
                            //currentPlayer.getUndo minus 1
                        } else if (answer == 1) {
                            //undo traded in cards
                            //currentPlayer.useUndo()
                        } else if (answer == 2) {
                            System.out.println("value of credits after buy: " + currentPlayer.getCredits());
                            currentPlayer.removeCredits(amountToBuy);
                            currentPlayer.useUndo();
                            System.out.println("value of credits after undo: " + currentPlayer.getCredits());
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
                        } else if (answer == 4) {
                            //skip turn undo soon

                        } else if (answer == 5) {
                            if (selection == 0) {
                                System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                                System.out.println("Here are number of undos of player after buying: " + currentPlayer.getNumberOfUndos());
                                currentPlayer.addCredits(1);
                                //once to remove the bought undo, and second to actually subtract the undo used
                                currentPlayer.useUndo();
                                currentPlayer.useUndo();
                                System.out.println("Here are number of credits of player after buying: " + currentPlayer.getCredits());
                                System.out.println("Here are number of undos of player after buying: " + currentPlayer.getNumberOfUndos());
                            } else if (selection == 1) {
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
                    } else if (currentPlayer.getNumberOfUndos() == 0) {
                        System.out.println("Sorry no undo's left");
                        bot.sendMessage("Sorry no undo's");
                    }
                }
                else {

                }
                tweetGame();
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

    public long getLastModified(){
        return lastModified;
    }
    public void setLastModified(long newModify){
        this.lastModified = newModify;
    }

    public int getCurrentLineInFile(){
        return currentLineInFile;
    }
    public void increaseCurrentLineInFile(){
        this.currentLineInFile++;
    }

    public void uploadFileToS3(){
        String key ="";
        String secKey = "";

        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            key = prop.getProperty("key");
            secKey = prop.getProperty("secKey");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        UploadObject myUpload = new UploadObject();
        String filePath = "fileToS3";
        myUpload.upload(key, secKey,"mikebitest05012018" , "gameStatus",  filePath);
    }





    public static void main(String []args){
        Graph myGame = new Graph();
        myGame.build_Graph();
        myGame.printMapAdjacencies();
        Deck theDeck = new Deck();
        Map myGameMap = new Map(myGame);
        Dice theDie = new Dice();





        Player_Controller gameController = new Player_Controller(2, myGameMap, theDeck, myGame);

        //Show who the Players are
        for(int i =0; i < gameController.playerList.size(); i++){
            List<Player> playerList = gameController.getPlayerList();
            System.out.println("Player " + i+ " is " + (playerList.get(i)).getPlayerName());
        }



        //allows players to claim countries and add initial armies to them
        gameController.fillMap();

        gameController.uploadFileToS3();
        //prints status of map so far
        //myGameMap.getMapStatus();
    }
}