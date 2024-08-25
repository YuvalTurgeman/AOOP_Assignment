/**
 * @author Yuval Turgeman id: 209299205
 * represents a Point class with two dimensionla quardinates.
 * @fields: x, y
 * @methods: ctors, getters + setters, equals, toString.
 **/

package utilities;

import java.util.Objects;

public class Point {

    //Fields:
    private double x;
    private double y;

    //ctor
    public Point(double x, double y) {
        if(x < 0 || x>1000000 || y < 0 || y > 100000)
            throw new IllegalArgumentException("Point coordinates out of bounds");
        this.x = x;
        this.y = y;
    }

    //methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x){
        if(x < 0 || x>1000000)
            throw new IllegalArgumentException("Point coordinates out of bounds");
        this.x = x;
    }

    public void setY(double y){
        if(y < 0 || y > 1000000)
            throw new IllegalArgumentException("Point coordinates out of bounds");
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        return getX() == ((Point)o).getX() && getY() == ((Point)o).getY();
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

}
