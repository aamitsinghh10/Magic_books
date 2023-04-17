package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.dto.LoginDTO;
import com.springboot.magicBooks.service.AdminService;
import com.springboot.magicBooks.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Map<String, List<String>> map,
                            Map<String, String> errormap,
                            @RequestParam(required = false) String error) {
        // GET
        if(error != null)
            errormap.put("error", error);
        List<String> roles = Arrays.asList("ADMIN","USER");
        map.put("roles", roles);
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session,
                         HttpServletResponse resp,
                         HttpServletRequest request)
    {
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals("email"))
                cookie.setMaxAge(0);
            else if(cookie.getName().equals("JSESSIONID"))
                cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        session.removeAttribute("email");
        session.removeAttribute("role");
        session.invalidate();
        return "redirect:/index";
    }

    @PostMapping("/login")
    public String loginPOstPage(LoginDTO dto, HttpServletRequest request,
                                HttpSession session, HttpServletResponse resp)
    {
        if(dto.getRole().equals("ADMIN")) {
            //login as admin
            //also set role in session
            if(this.adminService.login(dto)) {
                session.setAttribute("username", this.adminService.getAdmin(dto.getEmail()).getUsername());
                session.setAttribute("email", dto.getEmail());
                session.setAttribute("role", "ADMIN");
                return "redirect:/admin";
            }
        }
        else  if(dto.getRole().equals("USER")){
            //login as user
            //set role in session
            if(this.userService.validateUser(dto)) {
                session.setAttribute("username", this.userService.getUser(dto.getEmail()).getUsername());
                session.setAttribute("email", dto.getEmail());
                session.setAttribute("role", "USER");
                return "redirect:/dashboard";
            }

        }
        return "redirect:login?error=Invalid credentials";
    }
}