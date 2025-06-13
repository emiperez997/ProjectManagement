package com.project.management.project_management.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.domain.model.Project.ProjectStatus;
import com.project.management.project_management.domain.model.User;
import com.project.management.project_management.domain.port.in.ProjectUseCase;
import com.project.management.project_management.domain.port.out.ProjectRepositoryPort;
import com.project.management.project_management.domain.port.out.UserRepositoryPort;

@Service
public class ProjectService implements ProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public ProjectService(ProjectRepositoryPort projectRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;

    }

    @Override
    public Project createProject(String name, String description, Long ownerId) {
        // Validate owner exists
        if (!userRepositoryPort.existsById(ownerId)) {
            throw new IllegalArgumentException("El propietario del proyecto no existe");
        }

        User owner = userRepositoryPort.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("El propietario del proyecto no existe"));

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setOwner(owner);
        project.setStatus(ProjectStatus.ACTIVE); // Default status

        return projectRepositoryPort.save(project);
    }

    @Override
    public Project updateProject(Long projectId, String name, String description, ProjectStatus status, Long userId) {

        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));

        if (!project.getOwner().getId().equals(userId)) {
            throw new IllegalArgumentException("Solo el propietario del proyecto puede actualizarlo");
        }

        project.setName(name);
        project.setDescription(description);
        project.setStatus(status);

        return projectRepositoryPort.save(project);
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));

        if (!project.getOwner().getId().equals(userId)) {
            throw new IllegalArgumentException("Solo el propietario del proyecto puede eliminarlo");
        }

        projectRepositoryPort.deleteById(projectId);
    }

    @Override
    public Project getProjectById(Long projectId, Long userId) {
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));

        // Optionally, you can check if the user is a member or owner of the project
        if (!project.getOwner().getId().equals(userId) && !project.getMembers().stream()
                .anyMatch(member -> member.getId().equals(userId))) {
            throw new IllegalArgumentException("El usuario no tiene acceso al proyecto");
        }

        return project;
    }

    @Override
    public List<Project> getUserProjects(Long userId) {
        System.out.println("Fetching projects for user with ID: " + userId);
        if (!userRepositoryPort.existsById(userId)) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        List<Project> ownedProjects = projectRepositoryPort.findByOwnerId(userId);
        List<Project> memberProjects = projectRepositoryPort.findByMemberId(userId);

        List<Project> allProjects = new ArrayList<>(ownedProjects);
        allProjects.addAll(memberProjects);

        return allProjects.stream()
                .distinct()
                .toList();
    }

    @Override
    public Project addMemberToProject(Long projectId, Long memberId, Long ownerId) {
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));

        if (!project.getOwner().getId().equals(ownerId)) {
            throw new IllegalArgumentException("Solo el propietario del proyecto puede agregar miembros");
        }

        User member = userRepositoryPort.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("El miembro no existe"));

        // Check if the member is already part of the project
        if (project.getMembers().stream().anyMatch(m -> m.getId().equals(memberId))) {
            throw new IllegalArgumentException("El miembro ya está en el proyecto");
        }

        project.getMembers().add(member);
        return projectRepositoryPort.save(project);
    }

    @Override
    public Project removeMemberFromProject(Long projectId, Long memberId, Long ownerId) {
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));

        if (!project.getOwner().getId().equals(ownerId)) {
            throw new IllegalArgumentException("Solo el propietario del proyecto puede eliminar miembros");
        }

        User member = userRepositoryPort.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("El miembro no existe"));

        // Check if the member is part of the project
        if (!project.getMembers().stream().anyMatch(m -> m.getId().equals(memberId))) {
            throw new IllegalArgumentException("El miembro no está en el proyecto");
        }

        project.getMembers().remove(member);
        return projectRepositoryPort.save(project);
    }

}
