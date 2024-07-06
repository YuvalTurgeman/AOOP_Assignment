/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type MbileEntity, extends Entity, Implements IMoblieEntity.
 * @fields: maxSpeed, Acceleration, speed
 * @methods: ctor, getters + setters, toString
 **/

package game.entities;

public abstract class MobileEntity extends Entity implements IMobileEntity{

    //fields + ctor
    private double maxSpeed;
    private double acceleration;
    private double speed;

    public MobileEntity(double maxSpeed, double acceleration){//maybe protected?
        if(maxSpeed <=0)
            throw new IllegalArgumentException("Cannot create mobile entity, max speed should be above 0");
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.speed = 0.0;
    }


    //getters + setters
    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        if(maxSpeed <= 0)
            throw new IllegalArgumentException("cannot set max Speed, max speed should be above 0");
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

    //toString
    public String toString(){
        return super.toString() + "this is class type " + getClass() +
               " the acceleration is: " + getAcceleration() +
               " the max speed is: " + getMaxSpeed() +
               " the current speed is: " + getSpeed();
    }
}
