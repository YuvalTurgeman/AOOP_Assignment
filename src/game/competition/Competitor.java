/**
 * @author Yuval Turgeman id: 209299205
 * represents an interface of competitor
 **/

package game.competition;

import game.arena.IArena;
import game.entities.IMobileEntity;


public interface Competitor extends IMobileEntity, Runnable {

    void initRace(IArena arena);

    void run(IArena arena);

    void setId(int id);
}
