package com.example.scribey.services;

import com.example.scribey.domain.user.RegisterDTO;
import com.example.scribey.domain.user.User;
import com.example.scribey.domain.user.UserRole;
import com.example.scribey.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(RegisterDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.email(), encryptedPassword, UserRole.USER, new Date().toString(), new Date().toString());
        userRepository.save(newUser);
    }
}
