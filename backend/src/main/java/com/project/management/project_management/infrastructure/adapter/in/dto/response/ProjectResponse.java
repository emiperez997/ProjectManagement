package com.project.management.project_management.infrastructure.adapter.in.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.project.management.project_management.domain.model.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private UserResponse owner;
    private List<UserResponse> members;
    private int totalTasks;
    private int completedTasks;

    public static ProjectResponse fromDomain(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setStatus(project.getStatus().name());
        response.setStartDate(project.getStartDate());
        response.setEndDate(project.getEndDate());
        response.setCreatedAt(project.getCreatedAt());
        response.setOwner(UserResponse.fromDomain(project.getOwner()));
        response.setMembers(project.getMembers().stream()
                .map(UserResponse::fromDomain)
                .toList());
        response.setTotalTasks(project.getTotalTasks());
        response.setCompletedTasks(project.getCompletedTasks());
        return response;
    }
}
