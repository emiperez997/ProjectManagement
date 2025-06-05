package com.project.management.project_management.infrastructure.adapter.out.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.domain.model.User;
import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.ProjectEntity;
import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.UserEntity;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());

        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());

        // Convert projects if available (avoid circular reference)
        if (entity.getOwnedProjects() != null) {
            List<Project> projects = entity.getOwnedProjects().stream()
                    .map(this::projectEntityToDomainBasic)
                    .collect(Collectors.toList());
            user.setProjects(projects);
        } else {
            user.setProjects(new ArrayList<>());
        }

        return user;
    }

    public User toDomainWithoutProjects(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setProjects(new ArrayList<>());

        return user;
    }

    // Basic conversion to avoid circular references
    private Project projectEntityToDomainBasic(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        Project project = new Project();
        project.setId(entity.getId());
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setCreatedAt(entity.getCreatedAt());
        project.setUpdatedAt(entity.getUpdatedAt());

        return project;
    }

    public List<User> toDomainList(List<UserEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<UserEntity> toEntityList(List<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }

        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
