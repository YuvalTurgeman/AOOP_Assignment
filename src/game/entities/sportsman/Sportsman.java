package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;

public abstract class Sportsman extends MobileEntity {

    //make getters and setters
    private String name;
    private double age;
    private Gender gender;

    public Sportsman(double maxSpeed, double acceleration, String name, double age, Gender gender){
        super(maxSpeed,acceleration);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //getters and setters, check input at setters?

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // not sure this class needs equals because there will never be an instance of Sportsman
    public String toString(){
        //todo:implement
        return "";
    }

}
