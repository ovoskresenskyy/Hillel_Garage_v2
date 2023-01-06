package com.example.hillel_garage_v2.repository.Dao;

import com.example.hillel_garage_v2.model.Car;

import java.util.List;

public interface CarRepository {

    List<Car> getAllByOwnerID(int ownerID);

    Car findById(int id);

    void save(Car Car);

    void update(Car car);

    void delete(int id);

}
