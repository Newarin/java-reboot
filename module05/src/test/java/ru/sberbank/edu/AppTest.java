package ru.sberbank.edu;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    @Test
    void main(){
        CityInfo moscow = new CityInfo("Moscow", new GeoPosition("55(45'4.4784'')", "37(37'6.3228'')"));
        //53° 12′ 10″ N, 50° 8′ 27″ E
        CityInfo samara = new CityInfo("Samara", new GeoPosition("53(12' 10'')", "50(8'27'')"));

        TravelService ts = new TravelService();

        ts.add(moscow);
        ts.add(samara);

        Assertions.assertThat(ts.getDistance("Moscow","Samara")).isEqualTo(855832);

        Assertions.assertThat(ts.getCitiesNear("Moscow",1000000).get(1).getName()).isEqualTo("Samara");
    }
}


