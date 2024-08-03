/**
 * @author Yuval Turgeman id: 209299205
 * represents a class of observable so that winterSportsman can "extend" more than one class
 **/

package game.competition;

import java.util.Observable;

public class CustomObservable extends Observable {
    private Competitor competitor;

    public CustomObservable(Competitor competitor) {
        this.competitor = competitor;
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    public Competitor getCompetitor() {
        return competitor;
    }


    public void notifyObservers() {
        setChanged(); // Mark the observable as changed
        super.notifyObservers();
    }


    public void notifyObservers(Object arg) {
        setChanged(); // Mark the observable as changed
        super.notifyObservers(arg);
    }
}
