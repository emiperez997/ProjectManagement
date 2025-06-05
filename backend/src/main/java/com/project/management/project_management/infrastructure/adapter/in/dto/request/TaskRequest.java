package com.project.management.project_management.infrastructure.adapter.in.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
  @NotBlank(message = "El título de la tarea es obligatorio")
  private String title;

  private String description;
  private String status;
  private String priority;
  private LocalDateTime dueDate;
  private Long assigneeId;

  @NotNull(message = "El ID del proyecto es obligatorio")
  private Long projectId;

  public TaskRequest(String title, String description, Long projectId) {
    this.title = title;
    this.description = description;
    this.projectId = projectId;
  }
}
