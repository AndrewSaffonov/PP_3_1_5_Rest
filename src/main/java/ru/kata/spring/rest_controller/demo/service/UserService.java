package ru.kata.spring.rest_controller.demo.service;

import ru.kata.spring.rest_controller.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> listAll();

    User getById(Long id);

    void deleteById(Long id);

    void update(User user);

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
