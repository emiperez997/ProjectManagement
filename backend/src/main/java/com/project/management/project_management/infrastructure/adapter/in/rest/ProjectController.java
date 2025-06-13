package com.project.management.project_management.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.domain.port.in.ProjectUseCase;
import com.project.management.project_management.domain.port.out.UserRepositoryPort;
import com.project.management.project_management.infrastructure.adapter.in.dto.request.ProjectRequest;
import com.project.management.project_management.infrastructure.adapter.in.dto.response.ProjectResponse;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectUseCase projectUseCase;
    private final UserRepositoryPort userRepository;

    public ProjectController(ProjectUseCase projectUseCase, UserRepositoryPort userRepository) {
        this.projectUseCase = projectUseCase;
        this.userRepository = userRepository;
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            return userRepository.findByUsername(username)
                    .map(user -> user.getId())
                    .orElseThrow(() -> new IllegalStateException("User not found"));
        }

        throw new IllegalStateException("Authentication principal is not of type UserDetails");
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> getUserProjects() {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Project> projects = projectUseCase.getUserProjects(ownerId);

        List<ProjectResponse> projectResponse = projects.stream()
                .map(project -> ProjectResponse.fromDomain(project))
                .toList();

        return ResponseEntity.ok(projectResponse);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest projectRequest) {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        Project project = projectUseCase.createProject(
                projectRequest.getName(),
                projectRequest.getDescription(),
                ownerId
        );

        return ResponseEntity.ok(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@RequestBody Long projectId, @RequestBody String name, @RequestBody Project.ProjectStatus status, @RequestBody String description) {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        Project project = projectUseCase.updateProject(projectId, name, description, status, ownerId);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        projectUseCase.deleteProject(projectId, ownerId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Project> addMemberToProject(@PathVariable Long projectId, @PathVariable Long memberId) {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        Project project = projectUseCase.addMemberToProject(projectId, memberId, ownerId);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Project> removeMemberFromProject(@PathVariable Long projectId, @PathVariable Long memberId) {
        Long ownerId = getAuthenticatedUserId();

        if (ownerId == null) {
            return ResponseEntity.badRequest().build();
        }

        Project project = projectUseCase.removeMemberFromProject(projectId, memberId, ownerId);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project);
    }

}
