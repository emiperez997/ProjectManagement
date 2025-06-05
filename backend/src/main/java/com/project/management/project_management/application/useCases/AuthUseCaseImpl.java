package com.project.management.project_management.application.useCases;

import com.project.management.project_management.domain.model.User;
import com.project.management.project_management.domain.port.in.AuthUseCase;
import com.project.management.project_management.domain.service.AuthService;

public class AuthUseCaseImpl implements AuthUseCase {

    private final AuthService authService;

    public AuthUseCaseImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public User register(String username, String email, String password, String firstName, String lastName) {
        return authService.register(username, email, password, firstName, lastName);
    }

    @Override
    public String login(String username, String password) {
        return authService.login(username, password);
    }

    @Override
    public User getCurrentUser(String token) {
        return authService.getCurrentUser(token);
    }

    @Override
    public boolean validateToken(String token) {
        return authService.validateToken(token);
    }

}
