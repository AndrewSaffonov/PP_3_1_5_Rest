package ru.kata.spring.boot_security.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/page")
    public String adminPage(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("principal",user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "admin_form";
    }

    @GetMapping("/redactor/{id}")
    public String getAdminRedactor(Model user, Model roles, @PathVariable("id") Long id) {
        roles.addAttribute("allRoles", roleService.findAll());
        user.addAttribute("users", userService.getUserById(id).get());
        return "/admin/admin_redactor";
    }

    @PatchMapping("/redactor/{id}")
    public String patchAdminRedactor(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.adminRedactor(user, id);
        return "redirect:/admin/page";
    }

    @DeleteMapping("/delete/{id}")
    public String adminDelete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/page";
    }

    @GetMapping("/registration")
    public String registrationGet(@ModelAttribute("newuser") User user, Model roles) {
        roles.addAttribute("allRoles", roleService.findAll());
        return "/admin/registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/page";
    }
}
