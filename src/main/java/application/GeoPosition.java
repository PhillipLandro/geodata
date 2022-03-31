package application;

public class GeoPosition {

    Latitude latitude;

    Longitude longitude;


    public GeoPosition(String geoPos){
        parseDegreeMinuteSecond(geoPos);
    }

    public GeoPosition(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Parsed den übergebenen Positionsstring, der das folgende
     * exemplarische Format besitzen muss:
     * N120°45'30"E19°12'34"
     * @param geoPos String
     */
    private void parseDegreeMinuteSecond(String geoPos){

        //TODO: Weiter machen!

        String delimiter = "[\"°']";

        String[] splitted = geoPos.split(delimiter);

        LatitudinalOrientation lat = splitted[0].charAt(0) == 'N' ? LatitudinalOrientation.NORTH : splitted[0].charAt(0) == 'S' ? LatitudinalOrientation.SOUTH: null;

        LongitudinalOrientation lon = splitted[3].charAt(0) == 'E' ? LongitudinalOrientation.EAST : splitted[3].charAt(0) == 'S' ? LongitudinalOrientation.WEST: null;

        if(splitted[0].charAt(0) == 'N'){
            this.latitude = new Latitude(LatitudinalOrientation.NORTH, // Orientation
                    (short)Integer.parseInt(splitted[0].substring(1)), // degree
                    (byte)Integer.parseInt(splitted[1]), // minutes
                    (byte)Integer.parseInt(splitted[2])); // seconds
        }else if(splitted[0].charAt(0) == 'S'){
            this.latitude = new Latitude(LatitudinalOrientation.SOUTH, // Orientation
                    (short)Integer.parseInt(splitted[0].substring(1)), // degree
                    (byte)Integer.parseInt(splitted[1]), // minutes
                    (byte)Integer.parseInt(splitted[2])); // seconds
        } else System.out.println("Ungültige Eingabe");

        if(splitted[3].charAt(0) == 'E')
            this.longitude = new Longitude(LongitudinalOrientation.EAST,
                    (short)Integer.parseInt(splitted[0].substring(1)),
                    (byte)Integer.parseInt(splitted[1]),
                    (byte)Integer.parseInt(splitted[2]));
        else if(splitted[3].charAt(0) == 'W')
            this.longitude = new Longitude(LongitudinalOrientation.WEST,
                    (short)Integer.parseInt(splitted[3].substring(1)),
                    (byte)Integer.parseInt(splitted[4]),
                    (byte)Integer.parseInt(splitted[5]));
        else System.out.println("Ungültige Eingabe");
    }

    /**
     * Parsed den übergebenen Positionsstring, der das folgende
     * exemplarische Format besitzen muss:
     * N120°45.24'E19°12.65'
     * @param geoPos String
     */
    private void parseDegreeMinuteWithDecimals(String geoPos){

    }

    public Longitude getLongitude() {
        return longitude;
    }

    public void setLongitude(Longitude longitude) {
        this.longitude = longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public void setLatitude(Latitude latitude) {
        this.latitude = latitude;
    }

    public String toString(){
        return this.latitude.getOrientation() + ""
                + this.latitude.getDegree() + "°"
                + this.latitude.getMinute() + "'"
                + this.latitude.getSecond() + '"'
                + this.longitude.getOrientation() + ""
                + this.longitude.getDegree() + "°"
                + this.longitude.getMinute() + "'"
                + this.longitude.getSecond() + '"';
    }

}
