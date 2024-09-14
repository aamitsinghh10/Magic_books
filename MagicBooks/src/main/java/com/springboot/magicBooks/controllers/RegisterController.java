package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.service.AdminService;
import com.springboot.magicBooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPostPage(RegisterDTO dto, Model model) {
        if (dto.getUserName() == null || dto.getUserName().isEmpty() || dto.getEmail() == null || dto.getEmail().isEmpty() || dto.getPassword() == null || dto.getPassword().isEmpty()) {
            model.addAttribute("error", "Please fill in all required fields.");
            return "register";
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            model.addAttribute("error", "Passwords don't match.");
            return "register";
        }

        try {
            if ("ADMIN".equals(dto.getRole())) {
                // Admin registration requires approval
                if (adminService.register(dto)) {
                    return "redirect:/login?pending=true";
                }
            } else if ("USER".equals(dto.getRole())) {
                if (userService.register(dto)) {
                    return "redirect:/login";
                }
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while registering. Please try again later.");
            return "register";
        }

        return "redirect:/login?error=invalid";
    }
}
