package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.UserDatabase;
import com.springboot.magicBooks.dto.LoginDTO;
import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDatabase userDatabase;

    public boolean validateUser(LoginDTO dto) {
        return true;
    }

    public boolean register(RegisterDTO dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUserName());
        try {
            this.userDatabase.save(user);
        }
        catch (Exception ex) {
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
