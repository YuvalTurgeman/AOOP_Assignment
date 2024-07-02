package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class WinterArena implements IArena{

    private double length;//maybe final?
    private SnowSurface surface;
    private WeatherCondition condition;

    public WinterArena(double len, SnowSurface sur, WeatherCondition wc){
        this.length = len;
        this.surface = sur;
        this.condition = wc;
    }//todo: add input checks


    @Override
    public double getFriction() {
        return surface.getFriction();
    }

    @Override
    public Boolean isFinished(IMobileEntity me) {
        return null;//todo: implement
    }
}
