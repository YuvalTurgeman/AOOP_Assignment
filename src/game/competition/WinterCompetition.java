package game.competition;

import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class WinterCompetition extends Competition{

    //fields:
    private Discipline discipline;
    private League league;
    private Gender gender;

    //ctor:
    public WinterCompetition(WinterArena arena, int maxCompetitors ,Discipline discipline, League league, Gender gender){
        super(arena, maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }

    //getters and setters, check input at setters
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
        //todo: implement
        return true;
    }

    public String toString(){
        //todo:implement
        return "";
    }

}
