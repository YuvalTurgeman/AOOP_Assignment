/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type VM_CompetitorPanel.
 *  this is the class that manages the creation of the competitor.
 *  @input: parameters come from the GUI
 * @output: an instance of competitor
 * @methods: createCompetitor.
 **/

package game.ViewModel;

import game.competition.Competitor;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Snowboarder;
import game.enums.Discipline;
import game.enums.Gender;

public class VM_CompetitorPanel {

    public Competitor createCompetitor(String name, String age, String maxSpeed, String acceleration, Gender gender, Discipline discipline, Class<?> instType, int id){
        Competitor competitor;
        switch (instType.getName()) {
            case "game.competition.SkiCompetition":
                competitor = new Skier(name, Double.parseDouble(age), gender,
                        Double.parseDouble(acceleration), Double.parseDouble(maxSpeed), discipline, id);
                break;
            case "game.competition.SnowboardCompetition":
                competitor = new Snowboarder(name, Double.parseDouble(age), gender,
                        Double.parseDouble(acceleration), Double.parseDouble(maxSpeed), discipline, id);
                break;
            default:
                throw new IllegalArgumentException("Unknown competition type: " + instType.getName());
        }
        return competitor;
    }

    public Competitor createDefaultCompetitor(String name,int id){
        Competitor competitor;//todo:maybe make it a field
        competitor = new Skier(name, 15, Gender.MALE,
                5, 25, Discipline.SLALOM, id);
        return competitor;
    }
}
