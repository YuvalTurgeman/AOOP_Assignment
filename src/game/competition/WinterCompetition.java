/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of competiton of type WinterCompetition, extends Competition
 * @fields: discipline, league, gender
 * @methods: ctor, getters + setters, isValidCompetitor, equals, toString
 * **/

package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class WinterCompetition extends Competition{

    //fields + ctor
    private Discipline discipline;
    private League league;
    private Gender gender;


    public WinterCompetition(WinterArena arena, int maxCompetitors , Discipline discipline, League league, Gender gender){
        super(arena, maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }

    public boolean isValidCompetitor(Competitor comp){
        if(comp instanceof WinterSportsman)
            return league.isInLeague(((WinterSportsman)comp).getAge()) && ((WinterSportsman)comp).getGender() == gender
                    && ((WinterSportsman)comp).getDiscipline() == discipline;
        return false;
    }

    //getters + setters
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    //equals + toString
    public boolean equals(Object other){
        if (other instanceof WinterCompetition)
            return getLeague() == ((WinterCompetition)other).getLeague() &&
                    getDiscipline() == ((WinterCompetition)other).getDiscipline() &&
                    getGender() == ((WinterCompetition)other).getGender();
        return true;
    }




    public String toString(){
        return "this is a winter competition, a sub-type of: " + super.toString();
    }

}
