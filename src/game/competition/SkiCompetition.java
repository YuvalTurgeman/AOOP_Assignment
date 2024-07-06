/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of competiton of type SkiCompetition, extends WinterCompetition
 * @methods: ctor, isValidCompetitor, equals, toString
 * **/

package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.Skier;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class SkiCompetition extends WinterCompetition {
    //ctor
    public SkiCompetition(WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors, discipline, league, gender);
    }

    //methods
    public boolean isValidCompetitor(Competitor comp) {
        if (comp instanceof Skier)
            return getLeague().isInLeague(((Skier) comp).getAge()) && ((Skier) comp).getGender() == getGender()
                    && ((Skier) comp).getDiscipline() == getDiscipline();

        return false;
    }

    //equals+tostring
    public boolean equals(Object other) {
        if (other instanceof SkiCompetition)
            return getArena() == ((SkiCompetition)other).getArena() &&
                    getActiveCompetitors() == ((SkiCompetition)other).getActiveCompetitors() &&
                    getDiscipline() == ((SkiCompetition)other).getDiscipline() &&
                    getGender() == ((SkiCompetition)other).getGender() &&
                    getFinishedCompetitors() == ((SkiCompetition)other).getFinishedCompetitors() &&
                    getLeague() == ((SkiCompetition)other).getLeague() &&
                    getMaxCompetitors() == ((SkiCompetition)other).getMaxCompetitors();
        return false;
    }

    public String toString() {
        return "This is a Ski competition and it's sub-type of " + super.toString();
    }

}
