package ru.sberbank.edu;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        // parse and set latitude and longitude in radian

        int degres = 0;
        int minutes = 0;
        double seconds = 0;

        String[] splitLatitude = latitudeGradus.split("\\(|\\'{1,2}\\)*");
        String[] splitLongitude = longitudeGradus.split("\\(|\\'{1,2}\\)*");
/**
 Считаем широту
 */
        for(int i =0; i <= splitLongitude.length; i++){
            switch (i) {
                case  (0):
                    degres = Integer.parseInt(splitLongitude[i]);
                    break;
                case (1):
                    minutes = Integer.parseInt(splitLongitude[i]);
                    break;
                case (2):
                    seconds = Double.parseDouble(splitLongitude[i]);
                    break;
                default:
                    break;
            }
        }
        longitude = ( degres + (minutes + seconds /60)/60) * Math.PI/180;
        /**
        Считаем долготу
        */
        for(int i =0; i <= splitLatitude.length; i++){
            switch (i) {
                case  (0):
                    degres = Integer.parseInt(splitLatitude[i]);
                    break;
                case (1):
                    minutes = Integer.parseInt(splitLatitude[i]);
                    break;
                case (2):
                    seconds = Double.parseDouble(splitLongitude[i]);
                    break;
                default:
                    break;
            }
        }
        latitude = ( degres + (minutes + seconds /60)/60) * Math.PI/180;
    }

    // getters and toString
}