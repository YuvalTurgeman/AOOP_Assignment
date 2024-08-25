package game.enums;


public enum SnowSurface {
    POWDER(0.7),
    CRUD(0.5),
    ICE(0.3);

    public static SnowSurface convert(String s) throws NoSuchFieldException {
        switch (s){
            case "Powder":
                return SnowSurface.POWDER;
            case "Crud":
                return SnowSurface.CRUD;
            case "Ice":
                return SnowSurface.ICE;
            default:
                throw new NoSuchFieldException("SnowSurface Does Not Exist");
        }
    }

    public final double friction;

    SnowSurface(double friction){
        this.friction = friction;
    }

    public double getFriction(){
        return friction;
    }
}
