package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.AdminDatabase;
import com.springboot.magicBooks.database.UserDatabase;
import com.springboot.magicBooks.entity.Admin;
import com.springboot.magicBooks.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDatabase userDatabase;

    @Autowired
    private AdminDatabase adminDatabase;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDatabase.findById(email).orElse(null);
        Admin admin = adminDatabase.findById(email).orElse(null);

        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        } else if (admin != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}

