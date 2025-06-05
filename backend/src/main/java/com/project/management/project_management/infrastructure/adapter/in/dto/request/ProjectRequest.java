package com.project.management.project_management.infrastructure.adapter.in.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
  @NotBlank(message = "El nombre del proyecto es obligatorio")
  private String name;

  private String description;
  private String status;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public ProjectRequest(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
