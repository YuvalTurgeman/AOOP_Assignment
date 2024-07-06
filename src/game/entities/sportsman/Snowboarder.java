/**
 * @author Yuval Turgeman id: 209299205
 * represesnts a competitor of type Snowboarder, extends WinterSportsman
 * @methods: ctor, equals, toString
 **/

package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

public class Snowboarder extends WinterSportsman{

    //ctor
    public Snowboarder(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(maxSpeed, acceleration, name, age, gender, discipline);
    }

    //equals + toString
    public boolean equals(Object other){
        if(other instanceof Snowboarder){
            return  getAge() == ((Snowboarder)other).getAge() &&
                    getDiscipline() == ((Snowboarder)other).getDiscipline() &&
                    getAcceleration() == ((Snowboarder)other).getAcceleration() &&
                    getGender() == ((Snowboarder)other).getGender() &&
                    getMaxSpeed() == ((Snowboarder)other).getSpeed() &&
                    getSpeed() == ((Snowboarder)other).getSpeed() &&
                    getName().equals(((Snowboarder)other).getName()) &&
                    getLocation() == ((Snowboarder)other).getLocation();
        }
        return false;
    }

    public String toString(){
        return super.toString() + " this is class " + getClass();
    }
}
