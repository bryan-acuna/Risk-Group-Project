package com.teamB;
import java.util.*;

public class Deck {
    List<Card> theDeck;

    //In the future, add all the countries to the countryPicture array, and change i to 42
    Deck(){
        theDeck = new ArrayList<>();
        for(int i =0; i < 4; i++){
            int j = i%3;
            Card newCard = new Card(armyPictures(j) ,countryPicture(i) );
            theDeck.add(newCard);
        }
        Card wildCard = new Card("WILD", countryPicture(4));
        Card wildCard2 = new Card("WILD", countryPicture(5));

        theDeck.add(wildCard);
        theDeck.add(wildCard2);
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

    public void drawCardFromDeck(Player playerReceivingCard){
        playerReceivingCard.takeCardFromDeck(theDeck.get(0));
        theDeck.remove(0);
    }

    public void undoDrawCardFromDeck(Player playerUndoing){
        int sizeOfHand = playerUndoing.getCardsInHand().size();
        theDeck.add(playerUndoing.getCardsInHand().get(sizeOfHand-1));
        playerUndoing.undoDrawCard();
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
