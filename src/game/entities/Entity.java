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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = new Point(location.getX(), location.getY());//not sure this deep copy is necessary
    }


    public String toString(){
        //todo:implement
        return "";
    }

    //dont think that equals necessary here because there will never be an instance of Entity
    public boolean equals(Object other){
        //todo:implement
        return true;
    }

}
