package ru.kata.spring.rest_controller.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.rest_controller.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}