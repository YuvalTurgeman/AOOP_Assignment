/**
 * @author Yuval Turgeman id: 209299205
 * represesnts an abstract class Entity
 * @fields: location
 * @methods: ctors, getters + setters, toString
 **/

package game.entities;

import utilities.Point;

public abstract class Entity {

    //fields + ctors
    private Point location;

    public Entity(){
        location = new Point(0,0);
    }

    public Entity(Point location){
        location = new Point(location.getX(), location.getY());//deep copy
    }

    public Entity(double x, double y){
        if(x<0)
            throw new IllegalArgumentException("can't create entity, cant set negative x");
        location = new Point(x,y);
    }

    //getters + setters
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = new Point(location.getX(), location.getY());//not sure this deep copy is necessary
    }

    public void setLocation(double x, double y){
        if(x<0)
            throw new IllegalArgumentException("can't set location of entity to a negative x");
    }

    //toString
    public String toString(){
        return "this is a class type: " + getClass() +
                " the location is: " + getLocation();
    }

}
