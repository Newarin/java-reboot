package ru.sberbank.edu.service;

import ru.sberbank.edu.model.Car;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

public interface CarService {

    void addCar(String id, String model) throws SQLException;

    void editModel(String id, String model) throws SQLException;

    void deleteCar(String id);

    /**
     *Реализуем переопределённые методы сервиса
     */
    Set<Car> createAll(Collection<Car> cars) throws SQLException;
    Set<Car> findAll() throws SQLException;
    Boolean deleteAll() throws SQLException;
    Set<Car> findByModel(String model) throws SQLException;



}
