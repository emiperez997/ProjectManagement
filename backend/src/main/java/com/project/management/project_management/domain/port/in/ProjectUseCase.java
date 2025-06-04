package com.project.management.project_management.domain.port.in;

import java.util.List;

import com.project.management.project_management.domain.model.Project;

public interface ProjectUseCase {

    Project createProject(String name, String description, Long ownerId);

    Project updateProject(Long projectId, String name, String description, Project.ProjectStatus status, Long userId);

    void deleteProject(Long projectId, Long userId);

    Project getProjectById(Long projectId, Long userId);

    List<Project> getUserProjects(Long userId);

    Project addMemberToProject(Long projectId, Long memberId, Long ownerId);

    Project removeMemberFromProject(Long projectId, Long memberId, Long ownerId);
}
