/**
 * @author Yuval Turgeman id: 209299205
 * represensts an interface of a Mobile Entity
 **/

package game.entities;

import utilities.Point;

public interface IMobileEntity {

    void move(double friction);

    Point getLocation();
}
