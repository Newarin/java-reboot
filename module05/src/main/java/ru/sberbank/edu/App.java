package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        CityInfo moscow = new CityInfo("Moscow", new GeoPosition("55(45'4.4784'')", "37(37'6.3228'')"));
        //53° 12′ 10″ N, 50° 8′ 27″ E
        CityInfo samara = new CityInfo("Samara", new GeoPosition("53(12' 10'')", "50(8'27'')"));

        TravelService ts = new TravelService();

        ts.add(moscow);
        ts.add(samara);

        System.out.println(ts.getDistance("Moscow","Samara"));
        System.out.println(ts.getCitiesNear("Moscow",1000000).get(1).getName());

    }
}
