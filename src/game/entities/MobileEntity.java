package game.entities;

public abstract class MobileEntity extends Entity implements IMobileEntity{

    private double maxSpeed;
    private double acceleration;
    private double speed;

    public MobileEntity(double maxSpeed, double acceleration){//maybe protected?
        this.maxSpeed = maxSpeed;//check > 0 ?
        this.acceleration = acceleration;//check >0
        this.speed = 0.0;
    }

    public void move(double friction){
        //todo:implement
    }

    //setters check range of input?
    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
