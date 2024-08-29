package game.entities.State;

public class Disabled implements State{

        public State getState() {
            return this;
        }

        public String toString() {
            return "Disabled";
        }
}
