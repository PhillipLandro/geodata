package application;

public class GeoData {

    private short degree;

    private byte minute;

    private byte second;

    public GeoData(short degree, byte minute, byte second){
        this.degree = degree;
        this.minute = minute;
        this.second = second;
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public void setDegree(short degree) {
        this.degree = degree;
    }

    public short getDegree() {
        return degree;
    }
}
