package application;

public abstract class GeoData {

    protected short degree;

    protected byte minute;

    protected byte second;

    public GeoData(short degree, byte minute, byte second){
        setDegree(degree);
        setMinute(minute);
        setSecond(second);
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
