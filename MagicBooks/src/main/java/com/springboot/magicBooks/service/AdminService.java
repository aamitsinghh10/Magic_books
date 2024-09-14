package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.AdminDatabase;
import com.springboot.magicBooks.dto.LoginDTO;
import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        admin.setApproved(false); // Default new admin registrations to unapproved

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
            if(admin.getPassword().equals(dto.getPassword()) && admin.isApproved()) {
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

    public List<Admin> getPendingAdmins() {
        return adminDatabase.findAllByApproved(false); // Assuming you have a method for this
    }

    public void approveAdmin(String email) {
        Admin admin = adminDatabase.findById(email).orElse(null);
        if (admin != null) {
            admin.setApproved(true);
            adminDatabase.save(admin);
        }
    }

    public void rejectAdmin(String email) {
        adminDatabase.deleteById(email);
    }
}

