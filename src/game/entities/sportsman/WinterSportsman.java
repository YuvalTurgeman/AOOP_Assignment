/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of type WinterSportsman, extends Sporsmna and implement Competitor
 * @fields: discipline
 * @methods: ctor, getters + setters, initRace, move, toString and equals(?)
 **/

package game.entities.sportsman;

import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competitor;
import game.competition.CustomObservable;
import game.entities.State.Active;
import game.entities.State.State;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

import javax.swing.*;

public /*abstract*/ class WinterSportsman extends Sportsman implements Competitor {

    //fields + ctor
    private static int GLOBAL_X = 0;
    private Discipline discipline;
    private boolean finished = false;
    private CustomObservable observable;
    private double newSpeed = 0;
    private JLabel icon;
    private int id;
    private State state;

    public WinterSportsman(){
        super(10,2,"default",10,Gender.MALE);//todo:never used, just so i can make the method "clone" here and be overridden, maybe unnecessary
        this.state = new Active();
    };

    public WinterSportsman(double maxSpeed, double acceleration, String name, double age, Gender gender, Discipline discipline,int id){
        super(maxSpeed, acceleration, name, age, gender);
        this.discipline = discipline;
        this.observable = new CustomObservable(this);
        this.id = id;
        setAcceleration((getAcceleration() + League.calcAccelerationBonus(getAge())));
        this.state = new Active();
    }

    //methods
    @Override
    public void initRace(IArena arena) {
        super.setLocation(new Point(GLOBAL_X,0));
        this.finished = false;
        GLOBAL_X += 50;
    }

    public void setIcon(JLabel icon){
        this.icon = icon;
    }

    public WinterSportsman clone(){return new WinterSportsman();};// will be overridden

    public void setId(int id){
        this.id = id;
    }

    @Override
    public void run(IArena arena) {
        while (!finished) {
            try {
                Thread.sleep(100); // Delay of 100 milliseconds for each iteration
                this.move(((WinterArena)arena).getFriction()); // Adjust friction as needed
                observable.setChanged();
                observable.notifyObservers();

                if (((WinterArena)arena).isFinished(this)) { // Check with the arena if the competitor has finished
                    finishRace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void finishRace() {
        this.finished = true;
        observable.setChanged();
        observable.notifyObservers("finished");
    }



    public void move(double friction) {
        double newSpeed = Math.min(getSpeed() + (getAcceleration() * friction), getMaxSpeed());
        setSpeed(newSpeed);
        getLocation().setY(getLocation().getY() + newSpeed);
        this.newSpeed = newSpeed;
//        observable.setChanged();
//        observable.notifyObservers();
    }

    public double getNewSpeed() {
        return newSpeed;
    }

    //getters + setters
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    //toString, (there will never be an instance of winterSportsman therefor equals is not necessary in this class)
    public String toString(){
        return super.toString() + " this is class type: " + getClass() +
                " the discipline is: " + getDiscipline();
    }

    public CustomObservable getObservable() {
        return observable;
    }



    @Override
    public void run() {

    }

    public int getID() { return id; }

    public void setID(int id) { this.id = id; }

    public State getState(){
        return state;
    }

}
