/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of competiton of type SnowboardCompetition, extends WinterCompetition
 * @methods: ctor, isValidCompetitor, equals, toString
 * **/

package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.Snowboarder;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class SnowboardCompetition extends WinterCompetition{

    //ctor
    public SnowboardCompetition(WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors, discipline, league, gender);
    }

    //methods
    public boolean isValidCompetitor(Competitor comp){
        if(comp instanceof Snowboarder)
            return super.getLeague().isInLeague(((Snowboarder)comp).getAge()) && ((Snowboarder)comp).getGender() == super.getGender()
                    && ((Snowboarder)comp).getDiscipline() == super.getDiscipline();
        return false;
    }

    //equals+tostring
    public boolean equals(Object other) {
        if (other instanceof SnowboardCompetition)
            return getArena() == ((SnowboardCompetition)other).getArena() &&
                    getActiveCompetitors() == ((SnowboardCompetition)other).getActiveCompetitors() &&
                    getDiscipline() == ((SnowboardCompetition)other).getDiscipline() &&
                    getGender() == ((SnowboardCompetition)other).getGender() &&
                    getFinishedCompetitors() == ((SnowboardCompetition)other).getFinishedCompetitors() &&
                    getLeague() == ((SnowboardCompetition)other).getLeague() &&
                    getMaxCompetitors() == ((SnowboardCompetition)other).getMaxCompetitors();
        return false;
    }
    public String toString(){
        return "This is a Snowboard competition and it's sub-type of " + super.toString();
    }
}
