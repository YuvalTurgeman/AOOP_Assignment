/**
 * @author Yuval Turgeman id: 209299205
 * represesnts a competitor of type Skier, extends WinterSportsman
 * @methods: ctor, equals, toString
 **/

package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

public class Skier extends WinterSportsman {

    //ctor
    public Skier(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(maxSpeed, acceleration, name, age, gender, discipline);
    }


    //equals + toString
    public boolean equals(Object other){
        if(other instanceof Skier){
            return  getAge() == ((Skier)other).getAge() &&
                    getDiscipline() == ((Skier)other).getDiscipline() &&
                    getAcceleration() == ((Skier)other).getAcceleration() &&
                    getGender() == ((Skier)other).getGender() &&
                    getMaxSpeed() == ((Skier)other).getSpeed() &&
                    getSpeed() == ((Skier)other).getSpeed() &&
                    getName().equals(((Skier)other).getName()) &&
                    getLocation() == ((Skier)other).getLocation();
        }
        return false;
    }

    public String toString(){
        return super.toString() + " this is class " + getClass();
    }

}
