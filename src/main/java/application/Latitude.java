package application;

import org.jetbrains.annotations.NotNull;

public class Latitude extends GeoData{

    LatitudinalOrientation orientation;

    public Latitude(@NotNull LatitudinalOrientation latitudinalOrientation, short degree, byte minute, byte second){
        super(degree, minute, second);
        this.orientation = latitudinalOrientation;
    }

    public LatitudinalOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(LatitudinalOrientation orientation) {
        this.orientation = orientation;
    }
}
