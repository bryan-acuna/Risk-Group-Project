public class Army {
    String controllingPlayer;
    int numberArmies;


    public String getControllingPlayer(){
        return controllingPlayer;
    }
    public void setControllingPlayer(String V){
        this.controllingPlayer = V;
    }

    public int getNumberArmies(){
        return numberArmies;
    }
    public int setNumberArmies(int V){
        return numberArmies = V;
    }

    public void print(){
        if(controllingPlayer != "None") {
            System.out.println("there is an Army here");
        }
    }

    Army(){
        numberArmies = 0;
        controllingPlayer = "None";
    }


}
