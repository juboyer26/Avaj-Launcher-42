package justine.simulator.aircraft;

import justine.simulator.Main;
import justine.simulator.towers.WeatherTower;
import justine.simulator.weather.*;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateCondtions() {

        if(this.weatherTower == null){
            System.out.println("Error occurred");
            return;
        }

        String currentWeather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> weatherMessages = new HashMap<String, String>();
        weatherMessages.put("RAIN", "It's raining. Better watch out for lightings.");
        weatherMessages.put("SNOW", "I love snow... :D");
        weatherMessages.put("FOG", "Cant see a damn thing.");
        weatherMessages.put("SUN", "Damn It is hot, thank goodness for my aircon.");
        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                break;
        }
        Main.writer
                .println("JetPlane#" + this.name + "(" + this.id + "): " + weatherMessages.get(currentWeather) + ".");
        if (coordinates.getHeight() <= 0) {
            Main.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + "is landing!.");
            this.weatherTower.unregister(this);
            Main.writer.println(
                    "Tower says JetPlane#" + this.name + "(" + this.id + ")" + " unregistered to weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Main.writer.println("Tower says JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}