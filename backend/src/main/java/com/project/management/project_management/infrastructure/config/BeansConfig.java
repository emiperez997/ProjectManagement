package com.project.management.project_management.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.management.project_management.domain.port.out.ProjectRepositoryPort;
import com.project.management.project_management.domain.port.out.TaskRepositoryPort;
import com.project.management.project_management.domain.port.out.UserRepositoryPort;
import com.project.management.project_management.infrastructure.adapter.out.persistance.adapter.ProjectRepositoryAdapter;
import com.project.management.project_management.infrastructure.adapter.out.persistance.adapter.TaskRepositoryAdapter;
import com.project.management.project_management.infrastructure.adapter.out.persistance.adapter.UserRepositoryAdapter;

@Configuration
public class BeansConfig {
  @Bean
  public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter adapter) {
    return adapter;
  }

  // @Bean
  // public ProjectRepositoryPort projectRepositoryPort(ProjectRepositoryAdapter
  // adapter) {
  // return adapter;
  // }

  // @Bean
  // public TaskRepositoryPort taskRepositoryPort(TaskRepositoryAdapter adapter) {
  // return adapter;
  // }

}
