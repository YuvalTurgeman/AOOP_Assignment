package game.entities;

import utilities.Point;

public abstract class Entity {
    private Point location;

    public Entity(){
        location = new Point(0,0);
    }

    public Entity(Point location){
        location = new Point(location.getX(), location.getY());//deep copy
    }
}
