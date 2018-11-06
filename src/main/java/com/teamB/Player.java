package com.teamB;

import java.util.*;


public class Player {
    private String playerName;
    private int playerID;
    private int armiesToPlace;
    private List<Card> cardsInHand;
    private int credits;
    private int numberOfUndos;
    private List<Army> countryStates; //a way to keep track of the state of a defending country
                                    //and be able to revert back to those states if undo
    private int []attackerAndDefenderCountryID;

    Player(){
        playerName = "Robot";
    }

    Player(String name, int ID){
        playerName = name;
        playerID = ID;
        cardsInHand = new ArrayList<>();
        credits =0;
        numberOfUndos = 0;
        attackerAndDefenderCountryID = new int[2];
        countryStates = new ArrayList<>();
        //increasePlayerCount();
    }

    public int[] getAttackerAndDefender(){
        return attackerAndDefenderCountryID;
    }
    public void buyUndo(){
        if(this.getCredits() >=1) {
            numberOfUndos += 1;
        }
        else{
            System.out.println("Not enough credits");
        }
    }

    public void useUndo(){
        numberOfUndos -= 1;
    }

    public int getNumberOfUndos(){
        return numberOfUndos;
    }

    public void tradeInCards(Card firstTradeIn, Card secondTradeIn, Card thirdTradeIn){
        if(cardsInHand.size() > 3) {
            System.out.println("Here are the cards you have: ");
            printCardsInHard();
            System.out.println("which would you like to trade in? Enter the index values of the cards");


            List<String> armyTypes = new ArrayList<>();
            armyTypes.add("INFANTRY");
            armyTypes.add("CALVARY");
            armyTypes.add("ARTILLERY");


            String firstCardValue = firstTradeIn.getArmyPicture();
            String secondCardValue = secondTradeIn.getArmyPicture();
            String thirdCardValue = thirdTradeIn.getArmyPicture();

            armyTypes.remove(firstCardValue);
            armyTypes.remove(secondCardValue);
            armyTypes.remove(thirdCardValue);

            if(armyTypes.size() == 0){
                armiesToPlace += 4;
                removeCardsFromHand(firstCardValue, secondCardValue, thirdCardValue);

            }
            else if(armyTypes.size() == 1 && (firstCardValue == "WILD" || secondCardValue == "WILD" || thirdCardValue == "WILD" )){
                armiesToPlace +=4;
                removeCardsFromHand(firstCardValue, secondCardValue, thirdCardValue);
            }
            else if(firstCardValue == secondCardValue  && secondCardValue== thirdCardValue){
                armiesToPlace += 4;
                removeCardsFromHand(firstCardValue, secondCardValue, thirdCardValue);
            }
            else{
                System.out.println("Sorry the cards you have chosen cannot be traded in");
            }
        }
    }

    public void transferCredits(Player giver, Player receiver, int amountOfCredits){
        if(giver.getCredits() >= amountOfCredits){
            giver.removeCredits(amountOfCredits);
            receiver.addCredits(amountOfCredits);
        }
        else{
            System.out.println("Insufficient credits");
        }
    }

    public void buyCredits(Player buyer, int amountOfCredits){
        System.out.println("you have just bought " + amountOfCredits);
        buyer.addCredits(amountOfCredits);
    }

    public void removeCardsFromHand(String card1, String card2, String card3){
        cardsInHand.remove(card1);
        cardsInHand.remove(card2);
        cardsInHand.remove(card3);
    }


    public void takeCardFromDeck(Card cardTaken){
        cardsInHand.add(cardTaken);
    }
    public void undoDrawCard(){
        cardsInHand.remove(cardsInHand.size()-1);
    }


    public List<Card> getCardsInHand(){
        return cardsInHand;
    }
    public void printCardsInHard(){
        for(int i=0; i < cardsInHand.size(); i++){
            cardsInHand.get(i).printCardValues();
        }
    }

    public void addCredits(int creditsToAdd){
        credits += creditsToAdd;
    }
    public void removeCredits(int creditsToRemove){
        credits -= creditsToRemove;
    }
    public int getCredits(){
        return credits;
    }
    //Sets player name
    public void setPlayerName(String name){
        playerName = name;
    }

    //Gets player name
    public String getPlayerName(){
        return playerName;
    }

    //Sets player ID
    public void setPlayerID(int ID){
        playerID = ID;
    }

    //Gets player ID
    public int getPlayerID(){
        return playerID;
    }

    public int getArmiesToPlace(){
        return armiesToPlace;
    }

    public void addArmiesToPlace(int n){
        armiesToPlace += n;
    }

    public List<Army> getCountryStates(){
        return countryStates;
    }

    //The problem with this is that we need to a roll dice per army, and b if win, gain control of country

    public boolean attack(Map gameMap, Graph myGame, Dice theDie,
                          Player attacker, String attackingCountry, int numberOfArmiesToAttackWith,
                          Player defender, String defendeingCountry,
                          String whoWon){
        countryStates.clear();


//        System.out.println("Select a country you own: ");
//        Scanner sc = new Scanner(System.in);
//        String myCountry = sc.nextLine().toUpperCase();
        String capsAttackingCountry = attackingCountry.toUpperCase();

        attackerAndDefenderCountryID[0] = gameMap.getHashMap().get(attackingCountry);



        //Scanner sc1 = new Scanner(System.in);
        //System.out.println("Adjacent Countries: " + myGame.getCountryAdjacency(attackingCountry));
        //System.out.println("Which country would you like to attack?");
        String capsdefendingCountry = defendeingCountry.toUpperCase();



        //Find the countryID from the country they want to attack
        //get the player name (string) who owns it and he will be defender
        //must find who it is though the playerList

        int countryID = gameMap.getHashMap().get(capsdefendingCountry);
        attackerAndDefenderCountryID[1] = countryID;
        String defenderName = gameMap.getMap().get(countryID).getControllingPlayer();
        PopUpNotify.infoBox(defenderName+" are under attack!", "Notify Player");
        //We have the defenders name, now we just need to find the player object
        //in the arrayList
//        for(int i =0; i < playerList.size(); i++){
//            if(defenderName == playerList.get(i).getPlayerName()){
//                defender = playerList.get(i);
//            }
//        }


        countryStates.add(gameMap.getMap().get(gameMap.getHashMap().get(capsAttackingCountry)));
        countryStates.add(gameMap.getMap().get(gameMap.getHashMap().get(capsdefendingCountry)));
        System.out.println("attack done");
        if(!myGame.getCountryAdjacency(capsAttackingCountry).contains(capsdefendingCountry)){
            return false;
        }

        //armies the attacker uses
        //System.out.println("How many armies would you like to attack with?");
        //while (!sc.hasNextInt())
            //sc.next();
        int armiesAttacking = numberOfArmiesToAttackWith;

        //armies the defender uses
        //System.out.println("How many armies would the opponent like to defend with?");
        //while (!sc.hasNextInt())
        //    sc.next();
        int armiesDefending = gameMap.getMap().get(countryID).getNumberArmies();
        //PopUpNotify.infoBox("you are being attacked: " + defenderName, "Notfiy Player");

        //Highest dice roll for attacker
        int attTopNum = 0;
        for(int x = 0; x < armiesAttacking; x++) {
            int temp = theDie.rollDice();
            if (attTopNum < temp) {
                attTopNum = temp;
            }
        }


        //Highest dice roll for defender
        int defTopNum = 0;
        for(int y = 0; y < armiesDefending; y++) {
            int temp = theDie.rollDice();
            if (defTopNum < temp) {
                defTopNum = temp;
            }
        }


        if(attTopNum > defTopNum) {
            System.out.println("Your army has won!");
            whoWon = "ATTACKER";


            //take control of country
            gameMap.removeOneArmy(capsAttackingCountry);
            gameMap.addArmy(capsdefendingCountry, attacker);
            gameMap.TakeOver(capsdefendingCountry, attacker.getPlayerName());

        }
        else if(attTopNum <= defTopNum) {
            System.out.println("Your army has lost!");
            whoWon = "DEFENDER";

            //subtract one army from yourself
            gameMap.subArmy(capsdefendingCountry, attacker, defender);
            gameMap.TakeOver(capsAttackingCountry, defender.getPlayerName());
        }




        //sc.close();
        return true;

    }

//    public int skipTurn(Player currentPlayer) {
//        int nextID;
//        System.out.println(currentPlayer.getPlayerName() + " has skipped his turn");
//        if(currentPlayer.getPlayerID() == currentPlayer.getPlayerCount()) {
//            nextID = 1;
//        }
//        else {
//            nextID = currentPlayer.getPlayerID() + 1;
//        }
//
//        System.out.println("Next player is player " + nextID);
//        return nextID;
//    }

//    public void reorganizeArmy() {
//
//    }

}