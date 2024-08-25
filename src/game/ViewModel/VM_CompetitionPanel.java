/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type VM_CompetitionPanel.
 *  this is the class that manages the creation of the competition.
 *  @input: parameters come from the GUI
 * @output: an instance of competition
 * @methods: ctor, createCompetition, getCompetition.
 **/

package game.ViewModel;

import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.SkiCompetition;
import game.competition.SnowboardCompetition;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class VM_CompetitionPanel {
    private VM_ArenaPanel vm_arenaPanel;
    private Competition competition;


    public VM_CompetitionPanel(VM_ArenaPanel arena){
        this.vm_arenaPanel = arena;
    }

    public Competition createCompetition(String maxCompetitors, String competitionType, String discipline, String league, String gender){
         {

                if (maxCompetitors.isEmpty() || Integer.parseInt(maxCompetitors) < 1 || Integer.parseInt(maxCompetitors) > 20) {
                    throw new IllegalArgumentException("Invalid Max Competitors Number, must be between 1-20");
                }

             try {
                int maxCompetitorsInt = Integer.parseInt(maxCompetitors);
                Discipline discipline1 = Discipline.convert(discipline);
                League league1 = League.convert(league);
                Gender gender1 = Gender.convert(gender);

                IArena arena = vm_arenaPanel.getArena();
                switch (competitionType){
                    case "Ski":
                    competition = new SkiCompetition((WinterArena) arena, maxCompetitorsInt ,discipline1,league1,gender1);
                        break;
                    case "Snowboard":
                    competition = new SnowboardCompetition((WinterArena) arena, maxCompetitorsInt ,discipline1,league1,gender1);
                        break;
                }

                return competition;

//            JOptionPane.showMessageDialog(view, "Competition Created Successfully");
            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(programWindow, "Please enter a valid number for the max competitors.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException | NoSuchFieldException ex) {
//                JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }
    }

    public Competition getCompetition() {
        return competition;
    }
}
