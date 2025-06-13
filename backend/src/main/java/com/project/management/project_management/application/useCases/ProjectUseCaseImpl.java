package com.project.management.project_management.application.useCases;

import java.util.List;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.domain.model.Project.ProjectStatus;
import com.project.management.project_management.domain.port.in.ProjectUseCase;
import com.project.management.project_management.domain.service.ProjectService;

public class ProjectUseCaseImpl implements ProjectUseCase {

    private final ProjectService projectService;

    public ProjectUseCaseImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Project createProject(String name, String description, Long ownerId) {
        return projectService.createProject(name, description, ownerId);
    }

    @Override
    public Project getProjectById(Long projectId, Long userId) {
        return projectService.getProjectById(projectId, userId);
    }

    @Override
    public Project updateProject(Long projectId, String name, String description, ProjectStatus status, Long userId) {
        return projectService.updateProject(projectId, name, description, status, userId);
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        projectService.deleteProject(projectId, userId);
    }

    @Override
    public List<Project> getUserProjects(Long userId) {
        List<Project> projects = projectService.getUserProjects(userId);

        System.out.println("Projects for user " + userId + ": " + projects);

        return projects;
    }

    @Override
    public Project addMemberToProject(Long projectId, Long memberId, Long ownerId) {
        return projectService.addMemberToProject(projectId, memberId, ownerId);
    }

    @Override
    public Project removeMemberFromProject(Long projectId, Long memberId, Long ownerId) {
        return projectService.removeMemberFromProject(projectId, memberId, ownerId);
    }

}
