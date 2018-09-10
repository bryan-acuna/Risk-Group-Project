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
            myGame.createInitialMap(6);
            myGame.bindCountriesToNumbers("USA");
            myGame.bindCountriesToNumbers("JPN");
            myGame.bindCountriesToNumbers("EU");


            myGame.bindCountriesToNumbers("CA");
            myGame.bindCountriesToNumbers("SA");
            myGame.bindCountriesToNumbers("AFRICA");


            myGame.addEdge(USA, CA);
            myGame.addEdge(USA, SA);
            myGame.addEdge(EU, AFICA);


            myGame.printMapAdjacencies();


            Map myGameMap = new Map(myGame);





            Player mike = new Player("Mike");
            Player bryan = new Player("Bryan");
            Player brandon = new Player("Brandon");

            myGameMap.addArmy(USA, mike);
            myGameMap.addArmy(AFICA, mike);
            myGameMap.addArmy(EU, mike);
            myGameMap.addArmy(JPN, mike);
            myGameMap.addArmy(CA, mike);
            myGameMap.addArmy(SA, mike);

            myGameMap.getMapStatus();

            System.out.println(myGameMap.isGameOver());

            //mike.attack(myGameMap, myGame);


            /*
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
            */

        }
    }
