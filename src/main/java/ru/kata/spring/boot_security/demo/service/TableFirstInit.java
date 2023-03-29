package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TableFirstInit {
    private final UserService userService;

    public TableFirstInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User admin = new User("admin@admin", "admin");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);
        admin.setRoles(adminRoles);

        userService.saveUser(admin);

        User user = new User("ivan@ivan", "ivanov");
        Role userRole1 = new Role("ROLE_USER");

        Set<Role> adminRoles1 = new HashSet<>();
        adminRoles1.add(userRole1);
        user.setRoles(adminRoles1);

        userService.saveUser(user);
    }
}