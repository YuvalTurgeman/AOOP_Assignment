
package utilities;

import java.util.Objects;

public class Point {

    //Fields:
    private double x;//should be final
    private double y;//should be final

    //ctor
    public Point(double x, double y) {
        if(x < 0 || x>1000000 || y < 0 || y > 800)
            throw new IllegalArgumentException("Point coordinates out of bounds"); //todo: exception type will be changed in the future
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
