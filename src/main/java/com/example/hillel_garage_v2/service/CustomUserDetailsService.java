package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.entity.SessionUser;
import com.example.hillel_garage_v2.repository.SessionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SessionUserRepository sessionUserRepository;

    @Autowired
    public CustomUserDetailsService(SessionUserRepository sessionUserRepository) {
        this.sessionUserRepository = sessionUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SessionUser sessionUser = sessionUserRepository.findByEmail(email);

        if (sessionUser != null) {
            return new User(sessionUser.getEmail(),
                    sessionUser.getPassword(),
                    List.of(sessionUser.getRole()));
        } else throw new UsernameNotFoundException("Invalid username or password.");
    }
}
