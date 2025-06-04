package com.project.management.project_management.domain.port.in;

import com.project.management.project_management.domain.model.User;

public interface AuthUseCase {

    User register(String username, String email, String password, String firstName, String lastName);

    String login(String username, String password);

    User getCurrentUser(String token);

    boolean validateToken(String token);
}
