package com.teamB;
import java.util.*;

public class Deck {
    List<Card> theDeck;

    //In the future, add all the countries to the countryPicture array, and change i to 42
    Deck(){
        theDeck = new ArrayList<>();
        for(int i =0; i < 6; i++){
            int j = i%3;
            Card newCard = new Card(armyPictures(j) ,countryPicture(i) );
            theDeck.add(newCard);
        }
    }

    private static String armyPictures(int i){
        String []armyTypes = {"INFANTRY", "CALVARY","ARTILLERY"};
        return armyTypes[i];
    }
    private static String countryPicture(int i){
        String []countryTypes = {"USA", "JPN", "EU", "CA", "SA", "AFRICA"};
        return countryTypes[i];
    }

    public void printDeck(){
        for(int i =0; i < theDeck.size(); i++){
            System.out.println("Card number " +i+ "has army picture" + theDeck.get(i).getArmyPicture() + " and the country: "+ theDeck.get(i).getCountryPicture());
        }
    }

    public void giveCard(Player playerReceivingCard){
        playerReceivingCard.takeCardFromDeck(theDeck.get(0));
        theDeck.remove(0);
    }

    public static void main(String[] args){
        //Tests that cards in deck are created
        //Also that when a player draws a card
        //The card is removed from deck and the player receives it
//        Deck gameDeck = new Deck();
//        gameDeck.printDeck();
//
//        Player mike = new Player("Mike", 0);
//        gameDeck.giveCard(mike);
//        System.out.println();

//        gameDeck.printDeck();
//        System.out.println();

//        mike.printCardsInHard();

    }



}
