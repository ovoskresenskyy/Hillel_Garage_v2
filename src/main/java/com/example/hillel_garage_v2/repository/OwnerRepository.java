package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {

    List<Owner> findAll();

}
