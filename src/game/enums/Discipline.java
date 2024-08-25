package game.enums;

/**
 * Created by itzhak on 24-Mar-19.
 */
public enum Discipline {
    SLALOM,
    GIANT_SLALOM,
    DOWNHILL,
    FREESTYLE;

    public static Discipline convert(String s) throws NoSuchFieldException {
        switch (s){
            case "Slalom":
                return Discipline.SLALOM;
            case "Giant Slalom":
                return Discipline.GIANT_SLALOM;
            case "Downhill":
                return Discipline.DOWNHILL;
            case "Freestyle":
                return Discipline.FREESTYLE;
            default:
                throw new NoSuchFieldException("Discipline Does Not Exist");
        }
    }
}
