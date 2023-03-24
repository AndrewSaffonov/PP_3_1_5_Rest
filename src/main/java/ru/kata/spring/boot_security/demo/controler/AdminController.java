package ru.kata.spring.boot_security.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/page")
    public String adminPage(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "/admin/adminpage";
    }

    @GetMapping("/redactor/{id}")
    public String getAdminRedactor(Model user, Model roles, @PathVariable("id") Long id) {
        roles.addAttribute("allRoles", roleServiceImpl.findAll());
        user.addAttribute("users", userServiceImpl.getUserById(id).get());
        return "/admin/admin_redactor";
    }

    @PatchMapping("/redactor/{id}")
    public String patchAdminRedactor(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.adminRedactor(user, id);
        return "redirect:/admin/page";
    }

    @DeleteMapping("/delete/{id}")
    public String adminDelete(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin/page";
    }

    @GetMapping("/registration")
    public String registrationGet(@ModelAttribute("newuser") User user, Model roles) {
        roles.addAttribute("allRoles", roleServiceImpl.findAll());
        return "/admin/registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("newuser") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin/page";
    }
}
/*
@Controller
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, PasswordEncoder passwordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/addUser")
    public String createUserForm(Model model) {
        List<Role> roles = roleServiceImpl.getRoles();
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user) {
        userServiceImpl.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/admin/editUser/{id}")
    public String editUserForm(Model model, @PathVariable("id") long id) {
        User user = userServiceImpl.getById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleServiceImpl.getRoles();
        model.addAttribute("roles", roles);
        return "editUser";
    }

    @PostMapping("/admin/editUser")
    public String editUser(User user) {
        userServiceImpl.editUser(user);
        return "redirect:/users";
    }

    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable("id") long id) {
        userServiceImpl.deleteUser(id);
        List<User> users = userServiceImpl.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
 */