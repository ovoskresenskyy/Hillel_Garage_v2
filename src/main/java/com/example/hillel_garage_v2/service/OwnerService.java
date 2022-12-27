package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.Dao.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OwnerService {

    private final CarService carService;
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CarService carService) {
        this.ownerRepository = ownerRepository;
        this.carService = carService;
    }

    public void addNew(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner findById(int id) {
        return ownerRepository.getById(id);
    }

    public Map<Owner, List<Car>> getAll() {
        return ownerRepository.getAll();
    }

    public void update(Owner incomingData) {
        ownerRepository.update(incomingData);
    }

    public void delete(int id) {
        ownerRepository.delete(id);
    }

//    public Car addCar(int ownerID, Car car) {
//        Car newCar = carService.addCar(ownerID, car);
//        ownersAndCars.get(getById(ownerID)).add(newCar);
//        return newCar;
//    }
//
//    public Car deleteCar(int ownerID, int carID) {
//        Car car = carService.getCar(carID);
//        ownersAndCars.get(getById(ownerID)).remove(car);
//        carService.deleteCar(carID);
//        return car;
//    }

}
