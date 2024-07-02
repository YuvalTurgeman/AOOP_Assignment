package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;

public abstract class Sportsman extends MobileEntity {

    //make getters and setters
    private String name;
    private Double age;
    private Gender gender;

    public Sportsman(String name, Double age, Gender gender){
        super()
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}
