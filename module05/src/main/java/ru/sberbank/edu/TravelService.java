package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel Service.
 */
public class TravelService {

    final double RADIUS = 6372795;

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();


    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        // do something
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        // do something
        cities.removeIf(f -> cityName.equalsIgnoreCase(f.getName()));
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(CityInfo::getName).toList();
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */

    /**
     *
     * Много долгих скучных вычислений
     *
     */
    public int getDistance(String srcCityName, String destCityName) {
        /**
         * подготовка данных для расчёта
        */
        double srcLongitude = cities.stream().filter(f -> f.getName().equals(srcCityName)).toList().get(0).getPosition().getLongitude();
        double srcLatitude = cities.stream().filter(f -> f.getName().equals(srcCityName)).toList().get(0).getPosition().getLatitude();

        double destLongitude = cities.stream().filter(f -> f.getName().equals(destCityName)).toList().get(0).getPosition().getLongitude();
        double destLatitude = cities.stream().filter(f -> f.getName().equals(destCityName)).toList().get(0).getPosition().getLatitude();

        double cosLatitudeSrc = Math.cos(srcLatitude);
        double cosLatitudeDest = Math.cos(destLatitude);
        double sinLatitudeSrc = Math.sin(srcLatitude);
        double sinLatitudeDest = Math.sin(destLatitude);
        double delta = destLongitude - srcLongitude;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        double y = Math.sqrt(Math.pow(cosLatitudeDest * sinDelta, 2) + Math.pow(cosLatitudeSrc * sinLatitudeDest - sinLatitudeSrc * cosLatitudeDest * cosDelta, 2));
        double x = sinLatitudeSrc * sinLatitudeDest + cosLatitudeSrc * cosLatitudeDest * cosDelta;

        double dist = Math.atan2(y, x) * RADIUS;

        return (int)dist;

    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<CityInfo> getCitiesNear(String cityName, int radius) {
        return cities.stream().filter(f -> ((this.getDistance(cityName, f.getName()) <= radius))).toList();
    }

}
