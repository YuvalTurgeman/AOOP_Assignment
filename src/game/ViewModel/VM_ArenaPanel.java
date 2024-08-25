/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type VM_ArenaPanel.
 *  this is the class that manages the creation of the arena.
 *  @input: parameters come from the GUI
 * @output: an instance of arena
 * @methods: buildArena, getArena.
 **/

package game.ViewModel;

import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class VM_ArenaPanel {
    private IArena arena;

    public IArena buildArena(String arenaLength, String snowSurface, String weatherConditions) {

            if (arenaLength.isEmpty() || Integer.parseInt(arenaLength) < 700 || Integer.parseInt(arenaLength) > 900) {
                throw new IllegalArgumentException("Invalid input values! Please try again. Arena Length must be between 700-900");
            }

        try {
            double doubleArenaLength = Double.parseDouble(arenaLength);
            SnowSurface surface = SnowSurface.convert((String) snowSurface);
            WeatherCondition wc = WeatherCondition.convert((String) weatherConditions);

            arena = new WinterArena(doubleArenaLength, surface, wc);



            return arena;
        } catch (NumberFormatException ex) {
            throw  new NumberFormatException(ex.getMessage());
//            JOptionPane.showMessageDialog(, "Please enter a valid number for the arena length.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | NoSuchFieldException ex ) {
//            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public IArena getArena(){
        return arena;
    }

}

