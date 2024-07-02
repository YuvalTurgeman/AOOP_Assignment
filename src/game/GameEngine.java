package game;


import game.competition.Competition;
import game.entities.sportsman.Sportsman;

public class GameEngine {
    //fields
    private static GameEngine instance = null;

    private GameEngine(){}

    //methods
    public static GameEngine getInstance(){
        if(instance == null)
            instance = new GameEngine();
        return instance;
    }

    public void startRace(Competition comp){
        //todo:implements
    }

    public void printResults(Competition comp){
        //todo:implements
    }

    public boolean equals(Object other){
        //todo:implement
        return true;
    }

    public String toString(){
        //implement
        return "";
    }

}
