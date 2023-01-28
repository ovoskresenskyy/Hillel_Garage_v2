package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    User findByEmail(String email);
}


