package com.example.hillel_garage_v2.repository.Dao;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;

import java.util.List;
import java.util.Map;

public interface OwnerRepository {

    void save(Owner owner);

    void update(Owner owner);

    void delete(int id);

    Owner getById(int id);

    Map<Owner, List<Car>> getAll();

}
