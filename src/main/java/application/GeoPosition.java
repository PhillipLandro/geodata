package application;

import org.jetbrains.annotations.NotNull;

public class GeoPosition {

    public static final String FORMAT_MINUTE_SECONDS = "[N,S]\\d{1,3}°\\d\\d?'\\d\\d?\"[E,W]\\d{1,3}°\\d\\d?'\\d\\d?\"";
    public static final String FORMAT_MINUTE_WITH_DECIMALS = "[N,S]\\d{1,3}°\\d\\d?.\\d\\d?'[E,W]\\d{1,3}°\\d\\d?.\\d?'";

    Latitude latitude;
    Longitude longitude;

    public GeoPosition(String geoPos){
        try{
            if(geoPos.matches(FORMAT_MINUTE_SECONDS)){
                parseDegreeMinuteSecond(geoPos);
            }
            else if(geoPos.matches(FORMAT_MINUTE_WITH_DECIMALS)){
                parseDegreeMinuteWithDecimals(geoPos);
            } else throw new IllegalArgumentException("No legal GeoPosition String");
        }catch(Exception e){
           e.printStackTrace();
        }

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
                                    : splitted[3].charAt(0) == 'W' ? LongitudinalOrientation.WEST
                                    : null;

        if(lat != null && lon != null){
            try{
                this.latitude = new Latitude(lat, // Orientation
                        (short)Integer.parseInt(splitted[0].substring(1)), // degree
                        (byte)Integer.parseInt(splitted[1]), // minutes
                        (byte)Integer.parseInt(splitted[2])); // seconds
                this.longitude = new Longitude(lon,
                        (short)Integer.parseInt(splitted[3].substring(1)),
                        (byte)Integer.parseInt(splitted[4]),
                        (byte)Integer.parseInt(splitted[5]));
            } catch (Exception e){
                e.printStackTrace();
            }
        } else throw new IllegalArgumentException();

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
                : splitted[3].charAt(0) == 'W' ? LongitudinalOrientation.WEST
                : null;

        if(lat != null && lon != null){
            try{
                byte seconds = (byte)(Double.parseDouble(0 + "." + splitted[2]) * 60);
                this.latitude = new Latitude(lat, // Orientation
                        (short)Integer.parseInt(splitted[0].substring(1)), // degree
                        (byte)Integer.parseInt(splitted[1].substring(0, 2)), // minutes
                        seconds); // seconds
                seconds = (byte)(Double.parseDouble(0 + "." + splitted[5]) * 60);
                this.longitude = new Longitude(lon,
                        (short)Integer.parseInt(splitted[3].substring(1)),
                        (byte)Integer.parseInt(splitted[4].substring(0, 2)),
                        seconds);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else throw new IllegalArgumentException();
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
