/**
* @author Yuval Turgeman id: 209299205
* represents an interface of Arenas
 **/

package game.arena;

import game.entities.IMobileEntity;

public interface IArena {

    double getFriction();

    Boolean isFinished(IMobileEntity me);
}
