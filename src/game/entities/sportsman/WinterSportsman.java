/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of type WinterSportsman, extends Sporsmna and implement Competitor
 * @fields: discipline
 * @methods: ctor, getters + setters, initRace, move, toString and equals(?)
 **/

package game.entities.sportsman;

import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

public class WinterSportsman extends Sportsman implements Competitor {

    //fields + ctor
    private Discipline discipline;

    public WinterSportsman(double maxSpeed, double acceleration, String name, double age, Gender gender, Discipline discipline){
        super(maxSpeed, acceleration, name, age, gender);
        this.discipline = discipline;
        setAcceleration((getAcceleration() + League.calcAccelerationBonus(getAge())));
    }

    //methods
    @Override
    public void initRace() {
        super.setLocation(new Point(0,0));
    }

    public void move(double friction) {
        double newSpeed = Math.min(getSpeed() + (getAcceleration() * friction), getMaxSpeed());
        setSpeed(newSpeed);
        getLocation().setX(getLocation().getX() + newSpeed);
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

}
