package application;

public class Longitude extends GeoData{

    LongitudinalOrientation orientation;

    public Longitude(LongitudinalOrientation longitudinalOrientation, short degree, byte minute, byte second) {
        super(degree, minute, second);
        setOrientation(longitudinalOrientation);
    }

    public LongitudinalOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(LongitudinalOrientation orientation) {
        this.orientation = orientation;
    }
}
