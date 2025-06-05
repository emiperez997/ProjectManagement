package com.project.management.project_management.infrastructure.adapter.in.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
  private Long id;
  private String title;
  private String description;
  private String status;
  private String priority;
  private LocalDateTime dueDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long projectId;
  private String projectName;
  private UserResponse assignee;
  private UserResponse createdBy;
}
