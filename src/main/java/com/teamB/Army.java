package com.teamB;

public class Army {
    private String controllingPlayer;
    private int numberArmies;

    Army(){
        numberArmies = 0;
        controllingPlayer = "None";
    }

//    Army(String name, int players){
//
//    }

    public String getControllingPlayer(){
        return controllingPlayer;
    }
    public void setControllingPlayer(String V){
        this.controllingPlayer = V;
    }
    public void setNumberArmies(int v){
        numberArmies = v;
    }
    public int getNumberArmies(){
        return numberArmies;
    }

    public void addArmy(){
        numberArmies = numberArmies + 1;
    }

    public void subArmy(){
        numberArmies = numberArmies - 1;
    }

    public void print(){
        if(controllingPlayer == "None") {
            System.out.println("No armies here");
        }
        else{
            System.out.println("here is the controller:" + getControllingPlayer()+ " and the number of armies: " + getNumberArmies());

        }
    }
}