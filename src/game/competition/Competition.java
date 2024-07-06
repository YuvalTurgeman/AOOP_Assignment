/**
 * @author Yuval Turgeman id: 209299205
 * represents an abstract class Competition
 * @fields: arena, maxCompetitors, activeCompetitors, finishedCompetitors
 * @methods: ctor, getters + setters, abstract isValidCompetitor, addCompetitor, playTurn, hasActiveCompetitors, toString
 **/

package game.competition;
import game.arena.IArena;
import game.entities.sportsman.Skier;

import java.util.ArrayList;

public abstract class Competition {

    //Fields + ctor
    private IArena arena;
    private int maxCompetitors;
    ArrayList<Competitor> activeCompetitors;
    ArrayList<Competitor> finishedCompetitors;


    public Competition(IArena arena, int maxCompetitors){
        this.arena = arena;
        if(maxCompetitors <=0)
            throw new IllegalArgumentException("cannot create competition, amount of maximum competitors must be above 0");
        this.maxCompetitors = maxCompetitors;
        activeCompetitors = new ArrayList<Competitor>();
        finishedCompetitors = new ArrayList<Competitor>();
    }

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
        if(maxCompetitors <=0)
            throw new IllegalArgumentException("cannot set max competitors, amount of maximum competitors must be above 0");
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
    public abstract boolean isValidCompetitor(Competitor comp);

    public void addCompetitor(Competitor comp){
        if(activeCompetitors.size() == maxCompetitors)
            throw new IllegalStateException("cannot add another competitor, Amount of competitors is at it's maximum");
        if(!isValidCompetitor(comp))
            throw new IllegalArgumentException("cannot add a competitor of type " + comp.toString() + " to arena of type " + arena.toString());//may need to change
        activeCompetitors.add(comp);
    }


    public void playTurn(){
        ArrayList<Competitor> tmpActive = new ArrayList<>(activeCompetitors);
        for(Competitor comp: tmpActive){
            comp.move(arena.getFriction());
            if(arena.isFinished(comp)){
                finishedCompetitors.add(comp);
                activeCompetitors.remove(comp);
            }
        }
    }

    public boolean hasActiveCompetitors(){
        return !activeCompetitors.isEmpty();
    }

    //toString

    public String toString(){
        return "Arena of type: " + arena.getClass() +
                "Amount of maximum competitors is: " + getMaxCompetitors() +
                "List of Active competitors: " + activeCompetitors.toString() +
                "List of finished competitors: " + finishedCompetitors.toString();
    }
}