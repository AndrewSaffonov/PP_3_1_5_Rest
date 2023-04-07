package ru.kata.spring.rest_controller.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.rest_controller.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    User getByeMail(String email);
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    User getByUsername(String username);
}