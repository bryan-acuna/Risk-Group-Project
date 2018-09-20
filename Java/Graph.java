import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph{

    private int nodes;
    private int countryCounter;   //used as an iterator to bind a country name to the hash Map(country ID)
    private List<String> listOfCountries[];
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
    /*
    The idea here is that we have a known amount of countries given in the form of @nodes. First we call the method
    createInitialMap(), this will create a 2d linkedList which we call @listOfCountries. Each index of the list will
    represent a country and that element will contain a linked list which holds adjacent countries. Next we must run
    bindCOuntriesToNumbers(), this will take the given country name, use it as a key to the value of its "countryID"
    (not really important what number, we just need to be able to use the name to find the correct index in the
    @listOfCountries.) and we will also alongside with that store that country name in a linked list @countriesInOrder
    this is really only important when we want to print all the adjacencies for a country. The important part for
    bindCOuntriesToNumbers() is that we must do this for everysingle country first, before using addEdge(). Next we
    begin building edges between countries. We do this with addEdge() this takes in the countryA and countryB which we
    are building an adjacency between. We use the hashmap @myVertices and use the countryName as a key to find the ID
    that is was assigned. With this ID we can then access the appropriate node in the list that correspounds that that
    that country. And since that index of the list @listOfCountries correspounds to another linked list, we can simply
    do a .add(countryB) to push it into the list. We must then also build the adjacency the opposite way as well.

    */
    public void createInitialMap(int nodes){
        this.nodes = nodes;
        listOfCountries = new LinkedList[nodes];
        for(int i =0; i < nodes; i++){
            listOfCountries[i] = new LinkedList<>();
        }
    }

    public void bindCountriesToNumbers(String nodeName){
        //Binds some country name to a specific index. That index
        //corresponds to a index in the listOfCountries in which the node represents the country
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
        listOfCountries[countryID].add(countryName);

        //create the edge going the other way as well
        int countryID2 = myVertices.get(countryName);
        listOfCountries[countryID2].add(nodeName);

    }

    public void printMapAdjacencies(){
        for(int i =0; i < countryCounter; i++) {
            System.out.print(countriesInOrder.get(i) + ":is adjacent to ");
            for (String countryName : listOfCountries[i]) {
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
        return listOfCountries[myVertices.get(countryName)];
    }

    public boolean isCountryAdjacent(String countryName, String questionableCountry){
        int countryID = myVertices.get(countryName);
        if(listOfCountries[countryID].contains(questionableCountry)){
            return true;
        }
        else{
            return false;
        }
    }

    public List<String> getCountriesInOrder(){
        return countriesInOrder;
    }










}