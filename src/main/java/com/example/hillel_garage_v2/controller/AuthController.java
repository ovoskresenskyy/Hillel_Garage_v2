package com.example.hillel_garage_v2.controller;

import com.example.hillel_garage_v2.model.User;
import com.example.hillel_garage_v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", User.builder()
                .build());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") User user, BindingResult result, Model model) {

        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null
                && existingUser.getEmail() != null
                && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", "IsExist", "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "security/users";
    }
}
