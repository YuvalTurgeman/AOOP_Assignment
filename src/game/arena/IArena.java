package game.arena;

import game.entities.IMobileEntity;

public interface IArena {

    Double getFriction();

    Boolean isFinished(IMobileEntity me);
}
