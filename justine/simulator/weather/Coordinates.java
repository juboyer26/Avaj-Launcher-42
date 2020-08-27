package justine.simulator.weather;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (height > 100)
            this.height = 100;
        else if (height < 0)
            this.height = 0;
        else
            this.height = height;

        if (latitude < 0 || longitude < 0) {
            this.latitude = 0;
            this.longitude = 0;
        } else {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
}
