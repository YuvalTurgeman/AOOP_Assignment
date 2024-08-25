/**
 * @author Yuval Turgeman id: 209299205
 * represesnts an Arena of type WinterArena
 * @fields: lengh, surdace, condition
 * @methods: ctor, getters + setters, getFriction, isFinished, equals, toString
 **/

package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class WinterArena implements IArena{

    //fields + ctors
    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;

    public WinterArena(double len, SnowSurface sur, WeatherCondition wc){
        if(len <=0)
            throw new IllegalArgumentException("length of arena should be above 0");
        this.length = len;
        this.surface = sur;
        this.condition = wc;
    }

    //methods
    @Override
    public double getFriction() {
        return surface.getFriction();
    }

    @Override
    public Boolean isFinished(IMobileEntity me) {
        return me.getLocation().getY()>=getLength();
    }

    //getters and setters

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if(length <= 0)
            throw new IllegalArgumentException("Cannot set the length of arena to be less than 1");
        this.length = length;
    }

    public SnowSurface getSurface() {
        return surface;
    }

    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }


    //equals + toString

    @Override
    public boolean equals(Object other) {
        if(other instanceof WinterArena)
            return  getLength() == ((WinterArena)other).getLength() &&
                    getCondition() == ((WinterArena)other).getCondition() &&
                    getFriction() == ((WinterArena)other).getFriction() &&
                    getSurface() == ((WinterArena)other).getSurface();
        return false;
    }

    @Override
    public String toString(){
        return             "This is arena of type" + getClass() +
                           " the surface is: " + getSurface() +
                           " the length is: " + getLength() +
                           " the conditions are: " + getCondition() +
                           " the friction is: " + getFriction();
    }
}
