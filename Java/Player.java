import java.util.*;
public class Player {
    private String playerName;
    private int playerID; 
    private int totalPlayers;

    Player(){
        playerName = "Robot";
    }
    Player(String name, int ID){
        playerName = name;
        playerID = ID;
        increasePlayerCount();
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
    
    
    //Increases total players
    public void increasePlayerCount() {
    	totalPlayers++;
    }
    
    //Returns total players
    public int getPlayerCount() {
    	return totalPlayers;
    }
    
    public void attack(Map gameMap, Graph myGame){
        System.out.println("Select a country you own: ");
        Scanner sc = new Scanner(System.in);
        String myCountry = sc.nextLine();
        System.out.println("Adjacent Countries: " + myGame.getCountryAdjacency(myCountry));
        
        System.out.println("Which country would you like to attack?");
        String countryAttacking = sc.nextLine();
        
        
        System.out.println("How many armies would you like to attack with?");
        while (!sc.hasNextInt()) 
        	sc.next();
        int armiesAttacking = sc.nextInt();
        
//        if(diceroll1(armiesAttacking) > diceroll2()) {
//        	System.out.println("Your army has won!");
        
//        	take control of countrySelected;
//        }
//        else if(diceroll1(armiesAttacking) <= diceroll2()) {
//        	System.out.println("Your army has lost!");
        
//        	subtract one army from yourself
//        }

        sc.close();

    }
    
    public int skipTurn(Player currentPlayer) {
    	int nextID;
    	System.out.println(currentPlayer.getPlayerName() + " has skipped his turn");
    	if(currentPlayer.getPlayerID() == currentPlayer.getPlayerCount()) {
    		nextID = 1;
    	}
    	else {
    		nextID = currentPlayer.getPlayerID() + 1;
    	}
    	
    	System.out.println("Next player is player " + nextID);
    	return nextID;
    }
    
    public void reorganizeArmy() {
    	
    }

}
