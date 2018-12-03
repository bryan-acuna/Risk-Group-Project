package com.teamB;

import java.util.*;


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
    public List<String> getCountriesInOrder(){
        return countriesInOrder;
    }
    public int getCountryCount() {
        return countries.size();
    }
    public String getIndividualMap(int i){
        int numberOfArmies = (countries.get(i)).getNumberArmies();
        String controllingPlayer = (countries.get(i)).getControllingPlayer();
        String theCountry = countriesInOrder.get(i);
        String temp = theCountry + " is controlled by " + controllingPlayer + " and has: " + numberOfArmies + " army(s)";
        return temp;
    }

    //at some point will probably need Graph object as parameter for some checking

    public boolean addArmy(String countryName, Player currentPlayer)
    {
        int countryID = countryToID.get(countryName);



        if(countriesAvailable > 0 && (countries.get(countryID)).getNumberArmies() == 0) {
            (countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).addArmy();
            countriesAvailable--;
            return true;
        }
        else if(countriesAvailable == 0 && (countries.get(countryID)).getControllingPlayer() == currentPlayer.getPlayerName() ){
            //(countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).addArmy();
            countriesAvailable--;
            return true;
        }
        else if(countriesAvailable == 0 && (countries.get(countryID)).getControllingPlayer() != currentPlayer.getPlayerName() ){
            System.out.println("You are unable to add an army to a country you do not control");
            return false;
        }

        else if(countriesAvailable > 0 && (countries.get(countryID)).getNumberArmies() != 0){
            System.out.println("You must fill the remaining territories");
            return false;
        }
        else{
            (countries.get(countryID)).addArmy();
            return true;
        }
    }
    public void removeOneArmy(String countryName){
        int countryID = countryToID.get(countryName);
        (countries.get(countryID)).subArmy();
    }

    public void TakeOver(String countryToClaim, String strongerPlayer){
        int countryID = countryToID.get(countryToClaim);
        (countries.get(countryID)).setControllingPlayer(strongerPlayer);
    }

    public void setCountriesAvailable(int countriesAvailable) {
        this.countriesAvailable = countriesAvailable;
    }
    public int getCountriesAvailable(){
        return countriesAvailable;
    }

    public void subArmy(String countryName, Player currentPlayer, Player otherPlayer)
    {
        int countryID = countryToID.get(countryName);

        (countries.get(countryID)).subArmy();

        if((countries.get(countryID)).getNumberArmies() == 0) {
            countries.get(countryID).setControllingPlayer("None");
            //System.out.println("You have lost your army. Defender takes over.");
            //countriesAvailable++;
            //addArmy(countryName, otherPlayer);
            //TakeOver();
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


    public boolean isGameOverCheck(){

        List myList = new ArrayList();


        for(int i =0; i < countries.size(); i++){
            myList.add(countries.get(i).getControllingPlayer());
        }
        boolean allEqual = new HashSet<String>(myList).size() <= 1;

        return allEqual;

    }
}