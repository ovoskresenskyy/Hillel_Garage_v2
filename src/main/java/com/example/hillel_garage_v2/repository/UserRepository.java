package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    Optional<User> findByEmail(String email);
}


