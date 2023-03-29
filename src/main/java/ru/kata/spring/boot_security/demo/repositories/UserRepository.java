package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    User findByEmailWithRoles(@Param("email") String email);
    User getUserByEmail (String email);
//    @Query("select e FROM Login e WHERE e.loginId =(:loginId) and e.password=(:password)")
//    List<Login> fetchByLoginId(@Param("loginId") String loginId, @Param("password") String password);
}