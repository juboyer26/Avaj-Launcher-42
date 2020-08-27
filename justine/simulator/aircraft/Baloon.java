package justine.simulator.aircraft;

import justine.simulator.Main;
import justine.simulator.towers.WeatherTower;
import justine.simulator.weather.*;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateCondtions() {

        if(this.weatherTower == null){
            System.out.println("Error occurred");
            return;
        }

        String currentWeather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> weatherMessages = new HashMap<String, String>();

        weatherMessages.put("RAIN", "Damn you rain! You messed up my baloon.");
        weatherMessages.put("SNOW", "It's snowing. We're gonna crash.");
        weatherMessages.put("FOG", "Let's enjoy the good weather and take some pics.");
        weatherMessages.put("SUN", "This is hot.");

        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                        coordinates.getHeight() + 5);
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                break;
        }
        Main.writer.println("Baloon#" + this.name + "(" + this.id + "): " + weatherMessages.get(currentWeather) + ".");
        if (coordinates.getHeight() <= 0) {
            Main.writer.println("Baloon#" + this.name + "(" + this.id + "): " + "is landing!.");
            this.weatherTower.unregister(this);
            Main.writer.println(
                    "Tower says Baloon#" + this.name + "(" + this.id + ")" + " unregistered to weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Main.writer.println("Tower says Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}