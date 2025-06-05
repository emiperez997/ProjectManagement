package com.project.management.project_management.domain.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.management.project_management.domain.model.User;
import com.project.management.project_management.domain.port.in.AuthUseCase;
import com.project.management.project_management.domain.port.out.UserRepositoryPort;
import com.project.management.project_management.infrastructure.security.JwtTokenProvider;

@Service
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User register(String username, String email, String password, String firstName, String lastName) {
        if (userRepositoryPort.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        if (userRepositoryPort.existsByEmail(email)) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        User user = new User(username, email, passwordEncoder.encode(password), firstName, lastName);

        return userRepositoryPort.save(user);
    }

    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public User getCurrentUser(String token) {
        if (validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            return userRepositoryPort.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }

        throw new RuntimeException("Token inválido o expirado");
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
