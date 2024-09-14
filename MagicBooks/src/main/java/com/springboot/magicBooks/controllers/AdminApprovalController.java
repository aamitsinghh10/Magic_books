package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.entity.Admin;
import com.springboot.magicBooks.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminApprovalController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/pending")
    public String viewPendingAdmins(Model model) {
        List<Admin> pendingAdmins = adminService.getPendingAdmins();
        model.addAttribute("pendingAdmins", pendingAdmins);
        return "adminApproval";
    }

    @PostMapping("/admin/approve")
    public String approveAdmin(String email) {
        adminService.approveAdmin(email);
        return "redirect:/admin/pending";
    }

    @PostMapping("/admin/reject")
    public String rejectAdmin(String email) {
        adminService.rejectAdmin(email);
        return "redirect:/admin/pending";
    }
}
