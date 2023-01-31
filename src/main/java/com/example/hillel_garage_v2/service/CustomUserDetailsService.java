package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.User;
import com.example.hillel_garage_v2.repository.RoleRepository;
import com.example.hillel_garage_v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,
                                    RoleRepository repository) {

        this.userRepository = userRepository;
        this.roleRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    roleRepository.findAllByUserId(user.getId()));
        } else throw new UsernameNotFoundException("Invalid username or password.");
    }
}
