package ru.kata.spring.rest_controller.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.rest_controller.demo.model.Role;
import ru.kata.spring.rest_controller.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TableFirstInit {
    private final UserServiceImpl userService;

    public TableFirstInit(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User user = userService.getUserByEmail("admin@mail.ru");
        if (user == null) {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            User admin = new User(adminRoles,"admin", "admin", (byte) 35, "admin@mail.ru", "admin@mail.ru", "admin");
            System.out.println('1');

            userService.save(admin);
        }

    }
}
/*
        User user1 = userService.getByeMail("ivan@dom.ru");
        if (user1 == null) {
            User user1 = new User("ivan@dom.ru", "ivanov", "ivan", "ivanov", 40);
            Role userRole1 = new Role("ROLE_USER");

            Set<Role> adminRoles1 = new HashSet<>();
            adminRoles1.add(userRole1);
            user1.setRoles(adminRoles1);

            userService.addUser(user1);
        }
 */