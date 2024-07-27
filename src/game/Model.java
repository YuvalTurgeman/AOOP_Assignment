package game;

import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.competition.SnowboardCompetition;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Snowboarder;
import game.enums.*;

import java.util.List;

public class Model {
    private IArena arena;
    private Competition competition;

    public IArena getArena() {
        return arena;
    }

    public void createArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.arena = new WinterArena(length, surface, condition);
    }

    public Competition getCompetition() {
        return competition;
    }

    public void createCompetition(String type, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        switch (type) {
            case "Ski":
                this.competition = new SkiCompetition((WinterArena) arena, maxCompetitors, discipline, league, gender);
                break;
            case "Snowboard":
                this.competition = new SnowboardCompetition((WinterArena) arena, maxCompetitors, discipline, league, gender);
                break;
        }
    }

    public void addCompetitor(String name, double age, double maxSpeed, double acceleration) {
        Competitor competitor = null;
        if (competition instanceof SkiCompetition) {
            competitor = new Skier(name, age, ((SkiCompetition) competition).getGender(), acceleration, maxSpeed, ((SkiCompetition) competition).getDiscipline());
        } else if (competition instanceof SnowboardCompetition) {
            competitor = new Snowboarder(name, age, ((SnowboardCompetition) competition).getGender(), acceleration, maxSpeed, ((SnowboardCompetition) competition).getDiscipline());
        }
        competition.addCompetitor(competitor);
    }

    public List<Competitor> getActiveCompetitors() {
        return competition.getActiveCompetitors();
    }
}
