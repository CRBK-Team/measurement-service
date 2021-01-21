package net.ddns.crbkproject.domain.event;

public class WeatherEvent extends Event {
    private String sensor;
    private int temp;
    private int hum;
    private int pre;

    private WeatherEvent() {
    }

    public WeatherEvent(String sensor, int temp, int hum, int pre) {
        this.sensor = sensor;
        this.temp = temp;
        this.hum = hum;
        this.pre = pre;
    }

    public String getSensor() {
        return sensor;
    }

    public int getTemp() {
        return temp;
    }

    public int getHum() {
        return hum;
    }

    public int getPre() {
        return pre;
    }
}
