package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class WinterArena implements IArena{

    private Double length;//maybe final?
    private SnowSurface surface;
    private WeatherCondition condition;

    public WinterArena(Double len, SnowSurface sur, WeatherCondition wc){
        this.length = len;
        this.surface = sur;
        this.condition = wc;
    }//todo: add input checks


    @Override
    public Double getFriction() {
        return 0.0;//todo: implement
    }

    @Override
    public Boolean isFinished(IMobileEntity me) {
        return null;//todo: implement
    }
}
