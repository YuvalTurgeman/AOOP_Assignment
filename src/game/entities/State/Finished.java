package game.entities.State;

public class Finished implements State{

    public State getState() {
        return this;
    }

    public String toString() {
        return "Finished";
    }
}
