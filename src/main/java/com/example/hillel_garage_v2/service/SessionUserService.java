package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.entity.SessionUser;
import com.example.hillel_garage_v2.enums.Role;
import com.example.hillel_garage_v2.repository.SessionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionUserService {

    private final SessionUserRepository sessionUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SessionUserService(SessionUserRepository sessionUserRepository,
                              PasswordEncoder passwordEncoder) {
        this.sessionUserRepository = sessionUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(SessionUser incomingData) {

        SessionUser sessionUser = SessionUser.builder()
                .name(incomingData.getName())
                .email(incomingData.getEmail())
                .password(passwordEncoder.encode(incomingData.getPassword()))
                .role(Role.ADMIN)
                .build();

        sessionUserRepository.save(sessionUser);
    }

    public SessionUser findByEmail(String email) {
        return sessionUserRepository.findByEmail(email);
    }

    public List<SessionUser> findAll() {
        return sessionUserRepository.findAll();
    }
}