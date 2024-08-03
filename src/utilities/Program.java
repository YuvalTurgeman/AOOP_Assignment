/**
 *
 */
package utilities;

import game.GameEngine;
import game.View.ProgramWindow;
import game.competition.SkiCompetition;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Snowboarder;
import game.enums.*;

/**
 * @author Yuval Turgeman
 * Main class(run demo)
 */
public class Program {

    public static void main(String[] args) {
        ProgramWindow programWindow = new ProgramWindow();
        programWindow.pack();
        programWindow.setLocationRelativeTo(null);
        programWindow.setVisible(true);
    }
}
