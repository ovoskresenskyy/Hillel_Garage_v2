package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Role;
import com.example.hillel_garage_v2.model.User;
import com.example.hillel_garage_v2.repository.RoleRepository;
import com.example.hillel_garage_v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User incomingData) {

        User savedUser = userRepository.save(User.builder()
                .name(incomingData.getName())
                .email(incomingData.getEmail())
                .password(passwordEncoder.encode(incomingData.getPassword()))
                .build());

        roleRepository.save(Role.builder()
                .role("USER")
                .userId(savedUser.getId())
                .build());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}