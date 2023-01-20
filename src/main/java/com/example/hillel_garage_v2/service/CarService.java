package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable(value = "allCars")
    public List<Car> findAllByOwnerID(int ownerID) {
        return carRepository.findAllByOwnerID(ownerID);
    }

    @Cacheable(value = "car", key = "#id")
    public Car findById(int id) {
        return carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }
}
