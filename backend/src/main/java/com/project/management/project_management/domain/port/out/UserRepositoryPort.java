package com.project.management.project_management.domain.port.out;

import java.util.Optional;

import com.project.management.project_management.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    void deleteById(Long id);
}
