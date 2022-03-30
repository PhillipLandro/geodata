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

    private void parseDegreeMinuteSecond(String geoPos){

        String[] directions = geoPos.split("" + '"');
        String[] latitude = directions[0].split("°");
        String[] longitude = directions[1].split("°");
        String[] timeLatitude = latitude[1].split("'");
        String[] timeLongitude = longitude[1].split("'");

        if(latitude[0].charAt(0) == 'N')
            this.latitude = new Latitude(LatitudinalOrientation.NORTH, // Orientation
                    (short)Integer.parseInt(latitude[0].substring(1)), // degree
                    (byte)Integer.parseInt(timeLatitude[0]), // minutes
                    (byte)Integer.parseInt(timeLatitude[1])); // seconds
        else if(latitude[0].charAt(0) == 'S')
            this.latitude = new Latitude(LatitudinalOrientation.SOUTH,
                    (short)Integer.parseInt(latitude[0].substring(1)),
                    (byte)Integer.parseInt(timeLatitude[0]),
                    (byte)Integer.parseInt(timeLatitude[1]));
        else System.out.println("Ungültige Eingabe");

        if(longitude[0].charAt(0) == 'E')
            this.longitude = new Longitude(LongitudinalOrientation.EAST,
                    (short)Integer.parseInt(longitude[0].substring(1)),
                    (byte)Integer.parseInt(timeLongitude[0]),
                    (byte)Integer.parseInt(timeLongitude[1]));
        else if(longitude[0].charAt(0) == 'W')
            this.longitude = new Longitude(LongitudinalOrientation.WEST,
                    (short)Integer.parseInt(longitude[0].substring(1)),
                    (byte)Integer.parseInt(timeLongitude[0]),
                    (byte)Integer.parseInt(timeLongitude[1]));
        else System.out.println("Ungültige Eingabe");

    }

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
