package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.dto.RegisterDTO;
import com.springboot.magicBooks.service.AdminService;
import com.springboot.magicBooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Map<String, List<String>> roles) {
        roles.put("roles",List.of("ADMIN","USER"));
        return "register";
    }

    @PostMapping("/register")
    public String registerPostPage(String username, String email, String password, String confirm_password,
                                   Model model, RegisterDTO dto) {
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null
                || password.isEmpty()) {
            model.addAttribute("error", "Please fill in all required fields.");
            return "register";
        }

        // validate the password confirmation
        if (!password.equals(confirm_password)) {
            model.addAttribute("error", "Passwords don't match.");
            return "register";
        }

        try {
            System.out.println("role" + dto.getRole());
            if (dto.getRole().equals("ADMIN")) {
                if (this.adminService.register(dto)) {
                    return "redirect:/login";
                }
            }
            else if (dto.getRole().equals("USER")) {
                if (this.userService.register(dto)) {
                    return "redirect:/login";
                }
            }
        } catch (Exception e) {
            // handle any exceptions that may occur during registration
            model.addAttribute("error", "An error occurred while registering. Please try again later.");
            return "register";
        }
        return "redirect:/login?error=invalid";
    }

}
