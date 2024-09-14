package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.UserDatabase;
import com.springboot.magicBooks.dto.LoginDTO;
import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDatabase userDatabase;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDatabase userDatabase, PasswordEncoder passwordEncoder) {
        this.userDatabase = userDatabase;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validateUser(LoginDTO dto) {
        Optional<User> userOpt = this.userDatabase.findById(dto.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(dto.getPassword(), user.getPassword());
        }
        return false;
    }

    public boolean register(RegisterDTO dto) {
        if (!dto.getRole().equals("USER")) {
            return false; // Only USER role is allowed for registration
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // Encode password
        user.setUsername(dto.getUserName());
        try {
            this.userDatabase.save(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public User getUser(String email) {
        Optional<User> userOpt = this.userDatabase.findById(email);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }
}
