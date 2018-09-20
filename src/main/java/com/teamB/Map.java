package com.teamB;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    private List<Army> countries;
    private int countriesAvailable;
    private HashMap<String, Integer> countryToID; //create a copy of the hasMap made before
    private List<String> countriesInOrder; //just used to be able to return the country from the countryID


    Map(){

    }

    Map(Graph graphObject){

        countries = new LinkedList<>();
        countryToID = new HashMap<>();
        countriesInOrder = new LinkedList<>();
        countriesInOrder = graphObject.getCountriesInOrder();

        //System.out.println(countriesInOrder);

        for(int i =0; i < countriesInOrder.size(); i++){
            countryToID.put(countriesInOrder.get(i), i);
            Army fakeArmy = new Army();

            //Just make a bunch of "indexes" to to the array to initialize all countries with objects of fakeArmy inside them
            countries.add(fakeArmy);


            countriesAvailable++;
        }


    }


    //at some point will probably need Graph object as parameter for some checking
    public void addArmy(String countryName, Player currentPlayer)
    {
        int countryID = countryToID.get(countryName);



        if(countriesAvailable > 0 && (countries.get(countryID)).getNumberArmies() == 0) {
            (countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).addArmy();
            countriesAvailable--;
        }
        else if(countriesAvailable == 0 && (countries.get(countryID)).getControllingPlayer() == currentPlayer.getPlayerName() ){
            //(countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).addArmy();
        }
        else if(countriesAvailable == 0 && (countries.get(countryID)).getControllingPlayer() != currentPlayer.getPlayerName() ){
            System.out.println("You are unable to add an army to a country you do no control");
        }

        else if(countriesAvailable > 0 && (countries.get(countryID)).getNumberArmies() != 0){
            System.out.println("You must fill the remaining territories");
        }
    }
    public List<Army> getMap(){
        return countries;
    }

    public HashMap<String, Integer> getHashMap(){
        return countryToID;
    }

    public void getMapStatus(){
        for(int i =0; i < countries.size(); i++){
            int numberOfArmies = (countries.get(i)).getNumberArmies();
            String controllingPlayer = (countries.get(i)).getControllingPlayer();
            String theCountry = countriesInOrder.get(i);
            System.out.println(theCountry + " is controlled by " + controllingPlayer + " and has: " + numberOfArmies + " of armies");
        }
    }

    /*
    Checks to see if game is over
    continue looking is set to true so we run the loop atleast once. The if statement checks that since
    all empty armies are
     */
    public boolean isGameOver(){
        boolean gameOver = false;


        boolean continueLooking = true;
        int countryIterator = 1;
        String samePlayer = countriesInOrder.get(0);
        while(continueLooking){

            if((countries.get(countryIterator)).getControllingPlayer() != "None" && countryIterator<(countries.size()) &&
                    (countries.get(countryIterator)).getControllingPlayer() == ((countries.get(countryIterator-1)).getControllingPlayer())){
                countryIterator++;
                gameOver = true;
                if(countryIterator == countries.size()-1){
                    continueLooking = false;
                }

            }



            else{
                gameOver = false;
                continueLooking = false;
                //System.out.println(gameOver);

            }
        }
        return gameOver;
    }
//    public int getCountriesAvailable(){
//        //return
//    }
    /*
    public void autoPopulate(int numberPlayers, Graph graphObject){
        int playerPieces;
        //Need to write case 2 and change player pieces after
        switch(numberPlayers){
            case 3:
                playerPieces = 4;
                break;
            case 4:
                playerPieces = 3;
                break;
            case 5:
                playerPieces = 2;
                break;
            case 6:
                playerPieces = 2;
                break;
        }
        Player array[] = new Player[numberPlayers];
        for(int i =0; i < numberPlayers; i++){
            String istring = Integer.toString(i);
            Player newPlayer = new Player("Player"+ istring);
            array[i] = newPlayer;
        }
        for(int i = 0; i < countriesAvailable; i++){
            Random rand = new Random();
            List<Integer> countriesClaimed = new ArrayList<>();
            //Finds a random index (which is a country), the element in that index contains an army object
            Boolean loopFalse = true;
            while(loopFalse){
                //int randomNum = rand.nextInt((max - min) + 1) + min;
                int randomNum = rand.nextInt((5) + 1);
                if(!countriesClaimed.contains(randomNum)) {
                    //write an if statement to make suer the selected randomNum is not repeated
                    (countries.get(randomNum)).setControllingPlayer((array[randomNum]).getPlayerName());
                    (countries.get(randomNum)).setNumberArmies((countries.get(randomNum)).getNumberArmies() + 1);
                    System.out.println((array[randomNum]).getPlayerName() + " is claiming: " + countriesInOrder.get(i));
                    countriesClaimed.add(randomNum);
                    loopFalse = false;
                }
            }
        }
    }
    */
}