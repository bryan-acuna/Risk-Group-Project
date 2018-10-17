package com.teamB;

import java.util.*;

public class Player {
    private String playerName;
    private int playerID;
    private int armiesToPlace;
    private List<Card> cardsInHand;


    Player(){
        playerName = "Robot";
    }

    Player(String name, int ID){
        playerName = name;
        playerID = ID;
        cardsInHand = new ArrayList<>();
        //increasePlayerCount();
    }

    public void takeCardFromDeck(Card cardTaken){
        cardsInHand.add(cardTaken);
    }
    public List<Card> getCardsInHand(){
        return cardsInHand;
    }
    public void printCardsInHard(){
        for(int i=0; i < cardsInHand.size(); i++){
            cardsInHand.get(i).printCardValues();
        }
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


    //The problem with this is that we need to a roll dice per army, and b if win, gain control of country

    public boolean attack(Map gameMap, Graph myGame, Dice theDie, Player attacker, Player defender){
        System.out.println("Select a country you own: ");
        Scanner sc = new Scanner(System.in);
        String myCountry = sc.nextLine().toUpperCase();


        System.out.println("Adjacent Countries: " + myGame.getCountryAdjacency(myCountry));


        System.out.println("Which country would you like to attack?");
        String countryAttacking = sc.nextLine().toUpperCase();

        if(!myGame.getCountryAdjacency(myCountry).contains(countryAttacking)){
            return false;
        }

        //armies the attacker uses
        System.out.println("How many armies would you like to attack with?");
        while (!sc.hasNextInt())
            sc.next();
        int armiesAttacking = sc.nextInt();

        //armies the defender uses
        System.out.println("How many armies would the opponent like to defend with?");
        while (!sc.hasNextInt())
            sc.next();
        int armiesDefending = sc.nextInt();

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

            //take control of country
            gameMap.removeOneArmy(myCountry);
            gameMap.addArmy(countryAttacking, attacker);
            gameMap.TakeOver(countryAttacking, attacker.getPlayerName());

        }
        else if(attTopNum <= defTopNum) {
            System.out.println("Your army has lost!");

            //subtract one army from yourself
            gameMap.subArmy(countryAttacking, attacker, defender);
            gameMap.TakeOver(myCountry, defender.getPlayerName());
        }

        sc.close();
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