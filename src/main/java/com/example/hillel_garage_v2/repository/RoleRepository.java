package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findAllByUserId(int id);
}


