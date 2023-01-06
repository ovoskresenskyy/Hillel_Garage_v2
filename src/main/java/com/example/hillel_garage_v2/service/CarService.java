package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.repository.Dao.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllByOwnerID(int ownerID) {
        return carRepository.getAllByOwnerID(ownerID);
    }

    public Car findById(int id) {
        return carRepository.findById(id);
    }

    public void addNew(Car car) {
        carRepository.save(car);
    }

    public void update(Car incomingData) {
        carRepository.update(incomingData);
    }

    public void delete(int id) {
        carRepository.delete(id);
    }

}
