package ru.sberbank.edu.service;


import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Override
    public Set<Car> createAll(Collection<Car> cars) throws SQLException {
        return carRepository.createAll(cars);
    }

    @Override
    public Set<Car> findAll() throws SQLException {
        return carRepository.findAll();
    }

    @Override
    public Boolean deleteAll() throws SQLException {
        return carRepository.deleteAll();
    }

    @Override
    public Set<Car> findByModel(String model) throws SQLException {
        return carRepository.findByModel(model);
    }

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    @Override
    public void deleteCar(String id) {
        try {
            carRepository.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
