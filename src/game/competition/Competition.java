package game.competition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.Sportsman;

import java.util.ArrayList;

public abstract class Competition {

    //Fields //make getters and setters
    private IArena arena;
    private int maxCompetitors;
    ArrayList<Competitor> activeCompetitors;
    ArrayList<Competitor> finishedCompetitors;


    //ctor
    public Competition(IArena arena, int maxCompetitors){
        this.arena = arena;
        this.maxCompetitors = maxCompetitors;
        activeCompetitors = new ArrayList<Competitor>();
        finishedCompetitors = new ArrayList<Competitor>();
    }//check maxCompetitors>0?

    //getters + setters:

    public IArena getArena() {
        return arena;
    }

    public void setArena(IArena arena) {
        this.arena = arena;
    }

    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
    }

    public ArrayList<Competitor> getActiveCompetitors() {
        return activeCompetitors;
    }

    public void setActiveCompetitors(ArrayList<Competitor> activeCompetitors) {
        this.activeCompetitors = activeCompetitors;
    }

    public ArrayList<Competitor> getFinishedCompetitors() {
        return finishedCompetitors;
    }

    public void setFinishedCompetitors(ArrayList<Competitor> finishedCompetitors) {
        this.finishedCompetitors = finishedCompetitors;
    }

    //methods
    public boolean isValidCompetitor(Competitor competitor){
        //todo:implement
        return true;
    }

    public void addCompetitor(Competitor competitor){
        //todo:implement

    }//why void and not boolean?


    public boolean playTurn(){
        //todo:implement
        //return currentArena.playTurn, maybe playTurn()
        return true;
    }

    public boolean hasActiveCompetitors(){
        //todo:implement
        return !activeCompetitors.isEmpty();
    }

    //toString + equals

    public String toString(){
        //todo:implement
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        //todo:implement
        return true;
    }

}