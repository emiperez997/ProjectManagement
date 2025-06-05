package com.project.management.project_management.infrastructure.adapter.in.dto.response;

import java.time.LocalDateTime;
import java.util.List;

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
}
