package com.example.hillel_garage_v2.controller;

import com.example.hillel_garage_v2.entity.SessionUser;
import com.example.hillel_garage_v2.enums.Role;
import com.example.hillel_garage_v2.service.SessionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final SessionUserService sessionUserService;

    @Autowired
    public AuthController(SessionUserService sessionUserService) {
        this.sessionUserService = sessionUserService;
    }

    @GetMapping("/index")
    public String home() {
        return "security/index";
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", SessionUser.builder()
                .role(Role.ADMIN)
                .build());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") SessionUser sessionUser, BindingResult result, Model model) {

        SessionUser existingSessionUser = sessionUserService.findByEmail(sessionUser.getEmail());
        if (existingSessionUser != null
                && existingSessionUser.getEmail() != null
                && !existingSessionUser.getEmail().isEmpty()) {
            result.rejectValue("email", "IsExist", "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", sessionUser);
            return "/register";
        }

        sessionUserService.saveUser(sessionUser);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", sessionUserService.findAll());
        return "security/users";
    }
}
