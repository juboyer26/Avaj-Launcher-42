package justine.simulator.aircraft;

import justine.simulator.towers.WeatherTower;

public interface Flyable {
    void updateCondtions();

    void registerTower(WeatherTower weatherTower);
}