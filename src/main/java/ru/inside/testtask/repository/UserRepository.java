package ru.inside.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inside.testtask.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByFirstName(String name);
}
