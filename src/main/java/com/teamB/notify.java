package com.teamB;

public interface notify {
    //This tells me who owns the country
    String ownerCountry(String name);

    //This notifies the Player that is under attack
    void underAttack(Player player);
}
