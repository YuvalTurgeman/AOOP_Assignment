package game.arena;

import game.entities.IMobileEntity;

public interface IArena {

    double getFriction();

    Boolean isFinished(IMobileEntity me);
}
