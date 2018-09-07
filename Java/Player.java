import java.util.*;
public class Player {
    private String playerName;

    Player(){
        playerName = "Robot";
    }
    Player(String name){
        playerName = name;
    }


    public void setPlayerName(String name){
        playerName = name;
    }
/*
    public void attack(Map gameMap, Graph adjacency){
        System.out.println("Select a country you own?");
        Scanner sc = new Scanner(System.in);
        String myCountry = sc.nextLine();





        sc.close();

    }
    */

    public String getPlayerName(){
        return playerName;
    }

}
