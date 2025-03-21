package ru.itis.roadhelp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.roadhelp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);
}
