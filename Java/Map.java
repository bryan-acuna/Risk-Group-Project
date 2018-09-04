import java.util.HashMap;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    private List<Army> countries;
    private int countriesAvailable;
    private HashMap<String, Integer> countryToID; //create a copy of the hasMap made before

    Map(){

    }

    Map(Graph graphObject){

        countries = new LinkedList<>();
        List<String> countriesInOrder = new LinkedList<>();
        countriesInOrder = graphObject.getCountriesInOrder();
        Army fakeArmy = new Army();

        for(int i =0; i < countriesInOrder.size(); i++){
            countryToID.put(countriesInOrder.get(i), i);
            countries.add(fakeArmy);
            countriesAvailable++;
        }

    }


    //at some point will probably need Graph object as parameter for some checking
    public void addArmy(String countryName, Graph graphObject, Player currentPlayer)
    {
        int countryID = countryToID.get(countryName);

        if(countriesAvailable > 0 && (countries.get(countryID)).getNumberArmies() == 0) {
            (countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).setNumberArmies(  (countries.get(countryID)).getNumberArmies()+1 );
            countriesAvailable--;
        }
        else if(countriesAvailable == 0){
            (countries.get(countryID)).setControllingPlayer(currentPlayer.getPlayerName());
            (countries.get(countryID)).setNumberArmies(  (countries.get(countryID)).getNumberArmies()+1 );
        }

        else{
            System.out.println("You must fill the remaining territories");
        }
    }

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

        for(int i = 0; i < numberPlayers; i++){
            Random rand = new Random();
            int randomNum = rand.nextInt((6 - 0) + 1) + 0;


        }



    }




}
