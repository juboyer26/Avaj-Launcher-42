package justine.simulator.weather;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();

    private static String[] weather = { "RAIN", "SNOW", "SUN", "FOG" };

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        int result = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
        return weather[result % 4];
    }
}