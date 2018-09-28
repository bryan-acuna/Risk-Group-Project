package com.teamB;

import java.util.*;
public class Player {
    private String playerName;
    private int playerID;



    Player(){
        playerName = "Robot";
    }
    Player(String name, int ID){
        playerName = name;
        playerID = ID;
         //increasePlayerCount();
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


    //The problem with this is that we need to a roll dice per army, and b if win, gain control of country

//    public void attack(Map gameMap, Graph myGame, Dice theDie){
//        System.out.println("Select a country you own: ");
//        Scanner sc = new Scanner(System.in);
//        String myCountry = sc.nextLine();
//
//        System.out.println("Adjacent Countries: " + myGame.getCountryAdjacency(myCountry));
//
//        System.out.println("Which country would you like to attack?");
//        String countryAttacking = sc.nextLine();
//
//
//        System.out.println("How many armies would you like to attack with?");
//        while (!sc.hasNextInt())
//            sc.next();
//        int armiesAttacking = sc.nextInt();
//
//        if(theDie.rollDice() > theDie.rollDice()) {
//        	System.out.println("Your army has won!");
//
//        	//take control of countrySelected;
//        }
//        else if(theDie.rollDice() <= theDie.rollDice()) {
//        	System.out.println("Your army has lost!");
//
//        	//subtract one army from yourself
//        }
//
//        sc.close();
//
//    }

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

    public void reorganizeArmy() {

    }

}