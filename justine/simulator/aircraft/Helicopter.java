package justine.simulator.aircraft;

import justine.simulator.Main;
import justine.simulator.towers.WeatherTower;
import justine.simulator.weather.*;
import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateCondtions() {

        if(this.weatherTower == null){
            System.out.println("Error occurred");
            return;
        }

        String currentWeather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> weatherMessages = new HashMap<String, String>();
        weatherMessages.put("RAIN", "Quick!! Wippers on.");
        weatherMessages.put("SNOW", "My rotor is going to freeze!");
        weatherMessages.put("FOG", "Help i cant see... :/");
        weatherMessages.put("SUN", "This is perfect condition for flying!");
        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(),
                        coordinates.getHeight());
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(),
                        coordinates.getHeight());
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                break;
        }
        Main.writer
                .println("Helicopter#" + this.name + "(" + this.id + "): " + weatherMessages.get(currentWeather) + ".");
        if (coordinates.getHeight() <= 0) {
            Main.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + "is landing!.");
            this.weatherTower.unregister(this);
            Main.writer.println(
                    "Tower says Helicopter#" + this.name + "(" + this.id + ")" + " unregistered to weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Main.writer
                .println("Tower says Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}