package justine.simulator.aircraft;

import justine.simulator.weather.Coordinates;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case "baloon":
                return new Baloon(name, coordinates);
            case "jetplane":
                return new JetPlane(name, coordinates);
            case "helicopter":
                return new Helicopter(name, coordinates);
            default:
                return null;
        }

    }
}