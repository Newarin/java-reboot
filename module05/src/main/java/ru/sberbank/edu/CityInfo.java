package ru.sberbank.edu;

/**
 * City info
 */
public class CityInfo {

    private String name;
    private GeoPosition position;

    public String getName() {
        return name;
    }

    public GeoPosition getPosition() {
        return position;
    }

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }
}