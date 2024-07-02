package game.entities;

public abstract class MobileEntity extends Entity implements IMobileEntity{

    private Double maxSpeed;
    private Double acceleration;
    private Double speed;

    public MobileEntity(double maxSpeed, double acceleration){//maybe protected?
        this.maxSpeed = maxSpeed;//check > 0 ?
        this.acceleration = acceleration;//check >0
        this.speed = (double) 0;
    }

    public void move(double friction){
        //todo:implement
    }



}
