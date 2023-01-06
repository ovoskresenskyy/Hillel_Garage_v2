package com.example.hillel_garage_v2.repository.Dao;

import com.example.hillel_garage_v2.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OwnerRepository {

    List<Owner> getAll();

    Owner findById(int id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(int id);

}
