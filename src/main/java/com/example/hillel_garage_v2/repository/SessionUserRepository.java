package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.entity.SessionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionUserRepository extends JpaRepository<SessionUser, Integer> {

    List<SessionUser> findAll();
    SessionUser findByEmail(String email);
}


