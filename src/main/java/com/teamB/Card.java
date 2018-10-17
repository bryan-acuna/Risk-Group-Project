package com.teamB;

public class Card {
    private String armyPicture;
    private String countryPicture;

    Card(String armyPicture,String countryPicture){
        this.armyPicture = armyPicture;
        this.countryPicture = countryPicture;
    }

    public String getArmyPicture(){
        return armyPicture;
    }
    public String getCountryPicture(){
        return countryPicture;
    }
    public void printCardValues(){
        System.out.println("Value of army: " + armyPicture + " and value of Country: " + countryPicture);
    }
}
