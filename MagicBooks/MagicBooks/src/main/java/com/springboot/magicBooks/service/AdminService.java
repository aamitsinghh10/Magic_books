package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.AdminDatabase;
import com.springboot.magicBooks.dto.LoginDTO;
import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminDatabase adminDatabase;

    public boolean register(RegisterDTO dto) {
        Admin admin = new Admin();
        admin.setEmail(dto.getEmail());
        admin.setUsername(dto.getUserName());
        admin.setPassword(dto.getPassword());

        try {
            this.adminDatabase.save(admin);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean login(LoginDTO dto) {
        Optional<Admin> adminOption = this.adminDatabase.findById(dto.getEmail());
        if(adminOption.isPresent()) {
            Admin admin = adminOption.get();
            if(admin.getPassword().equals(dto.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Admin getAdmin(String email) {
        Optional<Admin> adminOption = this.adminDatabase.findById(email);

        if(adminOption.isPresent()) {
            return adminOption.get();

        }
        return null;

    }
}
