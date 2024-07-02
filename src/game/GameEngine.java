package game;


import game.competition.Competition;
import game.entities.sportsman.Sportsman;

public class GameEngine {
    //fields
    private Competition comp;
    private GameEngine instance = null;

    private GameEngine(){}

    //methods
    public GameEngine getInstance(){
        if(this.instance == null)
            this.instance = new GameEngine();
        return this.instance;
    }

    public boolean setArena(Object arena){
        //todo:implement
        return comp.setArena();
    }//i think this is the correct implementation

    public void initRace(){
        //todo:implement
        comp.initRace();
    }

    public void addRacer(Object newRacer){//not sure this is the correct return type
        //todo:implement
        comp.add((Sportsman)newRacer);
    }

    public void setComp(Competition comp){
        //todo:implement
    }

    public void startRace(){
        //todo:implement
    }

}
