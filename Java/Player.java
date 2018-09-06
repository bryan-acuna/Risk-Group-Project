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

    public String getPlayerName(){
        return playerName;
    }

}
