package game.entities.sportsman;

import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;

public class WinterSportsman extends Sportsman implements Competitor {
    private Discipline discipline;


    public WinterSportsman(double maxSpeed, double acceleration, String name, Double age, Gender gender, Discipline discipline){
        super(maxSpeed, acceleration, name, age, gender);
        this.discipline = discipline;
    }


    @Override
    public void initRace() {
        //todo:implememnt
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    //equals + toString

}
