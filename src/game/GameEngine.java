/**
 * @author Yuval Turgeman id: 209299205
 * represesnts a Singleton GameEngine class
 * @fields: instance
 * @methods: ctor, getInstance, startRace, printResults, toString
 **/

package game;


import game.competition.Competition;
import game.competition.Competitor;
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
        int steps = 1;
        for (Competitor competitor: comp.getActiveCompetitors())
            competitor.initRace();
        while(comp.hasActiveCompetitors()){
            comp.playTurn();
            steps++;
        }
        System.out.println("Race finished in " + steps + " steps");
        printResults(comp);
    }

    public void printResults(Competition comp){
        int i = 1;
        System.out.println("Race results: ");
        for(Competitor competitor: comp.getFinishedCompetitors()){
            System.out.println(i + ". " + ((Sportsman)competitor).getName());//what if it is not sportsman?
            i++;
        }

    }

    //toString (singleton doesn't need equals)
    public String toString(){
        if(instance == null)
            return "this game instance is not initialized";
        return "this is a singleton class of type " + getClass() + " it's info is: " + getInstance().toString();
    }

}
