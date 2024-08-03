/**
 * @author Yuval Turgeman id: 209299205
 * represesnts a Singleton GameEngine class
 * @fields: instance
 * @methods: ctor, getInstance, startRace, printResults, toString
 **/

package game;


import game.competition.Competition;
import game.competition.Competitor;
import game.competition.CustomObservable;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine {
    //fields
    private static GameEngine instance = null;
    boolean isRaceRunning;

    private GameEngine(){}

    //methods
    public static GameEngine getInstance(){
        if(instance == null)
            instance = new GameEngine();
        return instance;
    }

//    public void startRace(Competition comp) {
//        int steps = 1;
//        for (Competitor competitor: comp.getActiveCompetitors())
//            competitor.initRace();
//
//        while(comp.hasActiveCompetitors()){
//            try {
//                Thread.sleep(30);
//            }catch( InterruptedException ex){
//
//            }
//            comp.playTurn();
//            steps++;
//        }
//        System.out.println("Race finished in " + steps + " steps");
//        printResults(comp);
//    }

//    public void startRace(Competition comp) {
//        IArena arena = comp.getArena();
//        for (Competitor competitor : comp.getActiveCompetitors())
//            competitor.initRace(arena);
//
//        comp.startCompetition(comp.getArena());
//        while (comp.hasActiveCompetitors()) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println("Race finished!");
//        printResults(comp);
//    }

    public void startRace(Competition comp) {
        isRaceRunning = true;
        comp.startCompetition(comp.getArena());  // Start the competition which now includes periodic position printing

        // Start a single timer to move all competitors
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comp.hasActiveCompetitors()) {
                    ((Timer) e.getSource()).stop();
                    printResults(comp);
                    return;
                }
                for (Competitor competitor : comp.getActiveCompetitors()) {
                    moveCompetitor(competitor);
                }
            }
        });
        timer.start();
    }

    private void moveCompetitor(Competitor competitor) {
        WinterSportsman sportsman = (WinterSportsman) competitor;
        double speed = sportsman.getNewSpeed(); // Retrieve the updated speed
        utilities.Point currentLocation = competitor.getLocation();
        currentLocation.setY(currentLocation.getY() + speed); // Move based on the updated speed
//        currentLocation.setX(currentLocation.getX()); // Move based on the updated speed

        sportsman.setLocation(currentLocation);

        // Notify observers about the update
        CustomObservable observable = sportsman.getObservable();
        observable.setChanged();
        observable.notifyObservers();
    }

    public void printResults(Competition comp){
        int i = 1;
        System.out.println("Race results: ");
        for(Competitor competitor: comp.getFinishedCompetitors()){
            System.out.println(i + ". " + ((Sportsman)competitor).getName());//what if it is not sportsman?
            i++;
        }
        isRaceRunning = false;


    }

    private boolean getRaceRunning(){
        return getRaceRunning();
    }
    //toString (singleton doesn't need equals)
    public String toString(){
        if(instance == null)
            return "this game instance is not initialized";
        return "this is a singleton class of type " + getClass() + " it's info is: " + getInstance().toString();
    }

}





//
//package game;
//
//import game.arena.IArena;
//import game.View.ImagePanel;
//import game.View.ProgramWindow;
//import game.competition.Competition;
//import game.competition.Competitor;
//import game.competition.CustomObservable;
//import game.competition.WinterCompetition;
//import game.entities.sportsman.Sportsman;
//import game.entities.sportsman.WinterSportsman;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GameEngine {
//    //fields
//    private static GameEngine instance = null;
//
//    private GameEngine() {}
//
//    //methods
//    public static GameEngine getInstance() {
//        if (instance == null)
//            instance = new GameEngine();
//        return instance;
//    }
//
//    public void startRace(Competition comp) {
//        comp.startCompetition(comp.getArena());  // Start the competition which now includes periodic position printing
//
//        // Start a single timer to move all competitors
//        Timer timer = new Timer(100, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!comp.hasActiveCompetitors()) {
//                    ((Timer) e.getSource()).stop();
//                    printResults(comp);
//                    return;
//                }
//
//                for (Competitor competitor : comp.getActiveCompetitors()) {
//                    moveCompetitor(competitor);
//                }
//            }
//        });
//        timer.start();
//    }
//
//    private void moveCompetitor(Competitor competitor) {
//        WinterSportsman sportsman = (WinterSportsman) competitor;
//        double speed = sportsman.getNewSpeed(); // Retrieve the updated speed
//        utilities.Point currentLocation = competitor.getLocation();
//        currentLocation.setY(currentLocation.getY() + speed); // Move based on the updated speed
//        currentLocation.setX(currentLocation.getX()); // Move based on the updated speed
//
//        sportsman.setLocation(currentLocation);
//
//        // Notify observers about the update
//        CustomObservable observable = sportsman.getObservable();
//        observable.setChanged();
//        observable.notifyObservers();
//    }
//
//    public void printResults(Competition comp) {
//        int i = 1;
//        System.out.println("Race results: ");
//        for (Competitor competitor : comp.getFinishedCompetitors()) {
//            System.out.println(i + ". " + ((Sportsman) competitor).getName());
//            i++;
//        }
//    }
//
//    @Override
//    public String toString() {
//        if (instance == null)
//            return "this game instance is not initialized";
//        return "this is a singleton class of type " + getClass() + " it's info is: " + getInstance().toString();
//    }
//}
