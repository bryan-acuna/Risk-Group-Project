import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph{

    private int nodes;
    private int countryCounter;   //used as an iterator to bind a country name to the hash Map(country ID)
    private List<String> arrayOfNodes[];
    private HashMap<String, Integer> myVertices; //Basically we can use the country name to identify where index it is


    private List<String> countriesInOrder;
    Graph(){
        nodes = 0;
        countryCounter = 0;
        myVertices = new HashMap<>();
        countriesInOrder = new LinkedList<>();
        //The set of keys (countryNames) returns out of order, but since the index of array is mapped in order
        //we have to use a linkedList to keep track of countries in order, see print Map adjacencies
    }

//Map functions
    public void createInitialMap(int nodes){
        this.nodes = nodes;
        arrayOfNodes = new LinkedList[nodes];
        for(int i =0; i < nodes; i++){
            arrayOfNodes[i] = new LinkedList<>();
        }
    }

    public void bindCountriesToNumbers(String nodeName){
        //Binds some country name to a specific index. That index
        //corresponds to a index in the arrayOfNodes in which the node represents the country
        //and the element (which is a linkedList) shows the adjacent countries.

        myVertices.put(nodeName, countryCounter);
        countriesInOrder.add(nodeName);
        countryCounter++;
    }

    public void addEdge(String nodeName, String countryName){
        //nodeName(which is the name of the country we are adding adjacencies for) is the key to an int value mapped to it
        //we call that the countryID, and in the arrayList[ID] has an element of linked list, and we add onto that list
        //the countryName that is adjacent to it

        int countryID = myVertices.get(nodeName);
        arrayOfNodes[countryID].add(countryName);

        //create the edge going the other way as well
        int countryID2 = myVertices.get(countryName);
        arrayOfNodes[countryID2].add(nodeName);

    }

    public void printMapAdjacencies(){
        for(int i =0; i < countryCounter; i++) {
            System.out.print(countriesInOrder.get(i) + ":is adjacent to ");
            for (String countryName : arrayOfNodes[i]) {
                System.out.print("->" + countryName);
            }
            System.out.println();
        }
    }



//Getters
    public int getCountryID(String countryName){
        int countryID = myVertices.get(countryName);
        return countryID;
    }

    public int getNumNodes(){
        return nodes;
    }

    public int getCountryCounter(){
        return countryCounter;
    }

    public List<String> getCountryAdjacency(String countryName){
        return arrayOfNodes[myVertices.get(countryName)];
    }

    public List<String> getCountriesInOrder(){
        return countriesInOrder;
    }










}