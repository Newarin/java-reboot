package ru.sberbank.edu;

import org.assertj.core.api.Assertions;
import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CarBootstrapTest {

    @Test
    void main() throws SQLException {
        String args;
        Server server = Server.createTcpServer().start();
        H2DbEmbedded.initDb();

        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.addCar("777", "Lada");
            carService.addCar("7", "Gazel");
            carService.addCar("77", "VAZ");
            carService.addCar("7777", "UAZ");

            // Test check start
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);

            // Test end
            carService.deleteCar("77");

            readAllCarsSql = "SELECT * FROM car";
            statement = H2DbEmbedded.getConnection().createStatement();
            resultSet = statement.executeQuery(readAllCarsSql);


            Assertions.assertThat(carService.findAll().toString()).isEqualTo("[Car{id='777', model='Lada'}, Car{id='7', model='Gazel'}, Car{id='7777', model='UAZ'}]");

            Assertions.assertThat(carService.findByModel("UAZ").toString()).isEqualTo("[Car{id='7777', model='UAZ'}]");

            Assertions.assertThat(carService.deleteAll()).isEqualTo(true);

            Assertions.assertThat(carService.findAll().isEmpty()).isEqualTo(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        server.stop();
    }
}