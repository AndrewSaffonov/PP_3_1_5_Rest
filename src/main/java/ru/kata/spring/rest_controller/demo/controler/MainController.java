package ru.kata.spring.rest_controller.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.rest_controller.demo.model.User;
import ru.kata.spring.rest_controller.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class MainController {
    private final UserServiceImpl userService;

    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) {
        User authenticatedUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("authenticatedUserRoles", authenticatedUser.getRoles());
        return "user";
    }

    @GetMapping("/admin")
    public String admin(ModelMap model, @ModelAttribute("newUser") User newUser, Principal principal) {
        User authenticatedUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("authenticatedUserRoles", authenticatedUser.getRoles());
        return "admin";
    }
}