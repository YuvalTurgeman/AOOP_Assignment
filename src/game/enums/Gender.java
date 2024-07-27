package game.enums;

/**
 * Created by itzhak on 24-Mar-19.
 */
public enum Gender {
    FEMALE,
    MALE;

    public static Gender convert(String s) throws NoSuchFieldException {
        switch (s){
            case "Male":
                return Gender.MALE;
            case "Adult":
                return Gender.FEMALE;
            default:
                throw new NoSuchFieldException("Gender Does Not Exist");
        }
    }
}
