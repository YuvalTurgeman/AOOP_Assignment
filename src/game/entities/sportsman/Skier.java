package game.entities.sportsman;

import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;

public class Skier extends WinterSportsman {
    public Skier(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(maxSpeed, acceleration, name, age, gender, discipline);
    }
}
