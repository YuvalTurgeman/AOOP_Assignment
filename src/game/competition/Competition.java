/**
 * @author Yuval Turgeman id: 209299205
 * represents an abstract class Competition
 * @fields: arena, maxCompetitors, activeCompetitors, finishedCompetitors
 * @methods: ctor, getters + setters, abstract isValidCompetitor, addCompetitor, playTurn, hasActiveCompetitors, toString
 **/


package game.competition;

import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;


import java.util.*;

public abstract class Competition implements Observer {

    private IArena arena;
    private int maxCompetitors;
    protected List<Competitor> activeCompetitors;
    protected List<Competitor> finishedCompetitors;
    private Timer updateTimer;
    private boolean isCompetitionRunning = false;
    private boolean isCompetitionfinished = false;

    public Competition(IArena arena, int maxCompetitors) {
        this.arena = arena;
        if (maxCompetitors <= 0)
            throw new IllegalArgumentException("Amount of maximum competitors must be above 0");
        this.maxCompetitors = maxCompetitors;
        activeCompetitors = Collections.synchronizedList(new ArrayList<>());
        finishedCompetitors = new CopyOnWriteArrayList<>();
    }

    public IArena getArena() {
        return arena;
    }

    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    public List<Competitor> getActiveCompetitors() {
        return activeCompetitors;
    }

    public List<Competitor> getFinishedCompetitors() {
        return finishedCompetitors;
    }

    public abstract boolean isValidCompetitor(Competitor comp);

    public void addCompetitor(Competitor comp) {
        if (activeCompetitors.size() == maxCompetitors)
            throw new IllegalStateException("Amount of competitors is at its maximum");
        if (!isValidCompetitor(comp))
            throw new IllegalArgumentException("Competitor does not fit to competition! Choose another competitor.");
        activeCompetitors.add(comp);
        ((WinterSportsman) comp).getObservable().addObserver(this); //TODO: might need to add sportsmen
    }

    public void startCompetition(IArena arena) {
        isCompetitionRunning = true;
        isCompetitionfinished = false;
        synchronized (activeCompetitors) {
            for (Competitor competitor : activeCompetitors) {
                competitor.initRace(arena);
                new Thread(() -> competitor.run(arena)).start(); // Anonymous function to pass arena
            }
        }
        // Start a timer to print positions every 30 milliseconds
        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                printPositions();
            }
        }, 0, 30); // Schedule at fixed rate of 30 milliseconds
        updateCompetitionStatus();
    }

    private void updateCompetitionStatus(){
        if (getActiveCompetitors().isEmpty()) {
            isCompetitionRunning = false;
            isCompetitionfinished = true;
        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (arg != null && arg.equals("finished")) {
            if (o instanceof CustomObservable) {
                Competitor competitor = ((CustomObservable) o).getCompetitor();
                finishedCompetitors.add(competitor);
                synchronized (activeCompetitors) {
                    activeCompetitors.remove(competitor);
                }
                if (activeCompetitors.isEmpty()) {
                    updateTimer.cancel();
                    displayResults();
                    updateCompetitionStatus();
                }
            }
        }
    }

    public boolean getIsRunning() {
        return isCompetitionRunning;
    }

    public boolean getIsFinished() {
        return isCompetitionfinished;
    }

    private void printPositions() {
        synchronized (activeCompetitors) {
            System.out.println("Current positions:");
            activeCompetitors.forEach(competitor -> System.out.println(competitor));
        }
    }

    private void displayResults() {
        System.out.println("Race finished! Results:");
        finishedCompetitors.forEach(competitor -> System.out.println(competitor + ": " + competitor.getLocation()));
    }

    public boolean hasActiveCompetitors() {
        synchronized (activeCompetitors) {
            return !activeCompetitors.isEmpty();
        }
    }
}