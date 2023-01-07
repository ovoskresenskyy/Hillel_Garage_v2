package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    List<Car> findAllByOwnerID(int id);

}
