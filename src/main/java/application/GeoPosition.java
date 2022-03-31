package application;

import java.util.InputMismatchException;

public class GeoPosition {

    Latitude latitude;

    Longitude longitude;


    public GeoPosition(String geoPos){
        if(geoPos.contains("\\.")){
            parseDegreeMinuteWithDecimals(geoPos);
        } else parseDegreeMinuteSecond(geoPos);
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

        String[] splitted = geoPos.split("[\"°']");

        LatitudinalOrientation lat = splitted[0].charAt(0) == 'N' ? LatitudinalOrientation.NORTH
                                    : splitted[0].charAt(0) == 'S' ? LatitudinalOrientation.SOUTH
                                    : null;

        LongitudinalOrientation lon = splitted[3].charAt(0) == 'E' ? LongitudinalOrientation.EAST
                                    : splitted[3].charAt(0) == 'S' ? LongitudinalOrientation.WEST
                                    : null;

        if(lat != null && lon != null){
            try{
                this.latitude = new Latitude(lat, // Orientation
                        (short)Integer.parseInt(splitted[0].substring(1)), // degree
                        (byte)Integer.parseInt(splitted[1]), // minutes
                        (byte)Integer.parseInt(splitted[2])); // seconds
                this.longitude = new Longitude(LongitudinalOrientation.WEST,
                        (short)Integer.parseInt(splitted[3].substring(1)),
                        (byte)Integer.parseInt(splitted[4]),
                        (byte)Integer.parseInt(splitted[5]));
            } catch (Exception e){
                e.printStackTrace();
            }
        } else System.out.println("Ungültige Eingabe");

    }

    /**
     * Parsed den übergebenen Positionsstring, der das folgende
     * exemplarische Format besitzen muss:
     * N120°45.24'E19°12.65'
     * @param geoPos String
     */
    private void parseDegreeMinuteWithDecimals(String geoPos){

        String[] splitted = geoPos.split("[\\.°']");

        LatitudinalOrientation lat = splitted[0].charAt(0) == 'N' ? LatitudinalOrientation.NORTH
                : splitted[0].charAt(0) == 'S' ? LatitudinalOrientation.SOUTH
                : null;

        LongitudinalOrientation lon = splitted[3].charAt(0) == 'E' ? LongitudinalOrientation.EAST
                : splitted[3].charAt(0) == 'S' ? LongitudinalOrientation.WEST
                : null;

        if(lat != null && lon != null){
            try{
                byte seconds = (byte)(Double.parseDouble(0 + "." + splitted[2]) * 60);
                this.latitude = new Latitude(lat, // Orientation
                        (short)Integer.parseInt(splitted[0].substring(1)), // degree
                        (byte)Integer.parseInt(splitted[1].substring(0, 2)), // minutes
                        seconds); // seconds
                seconds = (byte)(Double.parseDouble(0 + "." + splitted[5]) * 60);
                this.longitude = new Longitude(LongitudinalOrientation.WEST,
                        (short)Integer.parseInt(splitted[3].substring(1)),
                        (byte)Integer.parseInt(splitted[4].substring(0, 2)),
                        seconds);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else System.out.println("Ungültige Eingabe");
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
