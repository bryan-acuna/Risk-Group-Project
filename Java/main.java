import java.util.*;
import java.util.HashMap;


public class main {

    //LinkedList<Integer> object = new LinkedList<Integer>();


    public static void main(String[] args){


        //Creates the graph we can reference for adjacency
        String USA = "USA";
        String JPN = "JPN";
        String EU = "EU";


        String CA = "CA";
        String SA = "SA";
        String AFICA = "AFRICA";


        Graph myGame = new Graph();
        myGame.createInitialMap(3);
        myGame.bindCountriesToNumbers("USA");
        myGame.bindCountriesToNumbers("JPN");
        myGame.bindCountriesToNumbers("EU");




        myGame.addEdge(USA, CA);
        myGame.addEdge(USA, SA);
        myGame.addEdge(EU, AFICA);


        myGame.printMapAdjacencies();




        List<String> adjacencyOfUSA = myGame.getCountryAdjacency(USA);
        if(adjacencyOfUSA.contains(CA)) {
            System.out.println("Yes");
        }


        List<String> adjacencyOfUSA2 = myGame.getCountryAdjacency(USA);

            if(adjacencyOfUSA2.contains(AFICA)){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }




        List<String> adjacencyOfJPN = myGame.getCountryAdjacency(JPN);
        if(adjacencyOfJPN.isEmpty()){
            System.out.println("empty");
        }
        for(String adjacency: adjacencyOfJPN){
            if(CA == adjacency){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
        }

    }
}
