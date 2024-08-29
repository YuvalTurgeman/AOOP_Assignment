/**
 * @author Yuval Turgeman id: 209299205
 * represesnts an abstract class of type Sportsman, extends MobileEntity
 *
 **/

package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;

public abstract class Sportsman extends MobileEntity {

    //fields + ctor
    private String name;
    private double age;
    private Gender gender;


    public Sportsman(double maxSpeed, double acceleration, String name, double age, Gender gender){
        super(maxSpeed,acceleration);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //getters + setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        if(age <=0 )
            throw new IllegalArgumentException("Age should be above 0");
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    //toString
    public String toString(){
        return  super.toString() + " This is class type: " + getClass() +
                " the name is: " + getName() +
                " the age is: " + getAge() +
                " the gender is: " + getGender();
    }


}
