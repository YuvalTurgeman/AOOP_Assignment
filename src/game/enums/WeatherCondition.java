package game.enums;

/**
 * Created by itzhak on 09-Mar-19.
 */
public enum WeatherCondition {
    SUNNY,
    CLOUDY,
    STORMY;

    public static WeatherCondition convert(String s) throws NoSuchFieldException {
        switch (s){
            case "Sunny":
                return WeatherCondition.SUNNY;
            case "Cloudy":
                return WeatherCondition.CLOUDY;
            case "Stormy":
                return WeatherCondition.STORMY;
            default:
                throw new NoSuchFieldException("Weather Condition Does Not Exist");
        }
    }
}
