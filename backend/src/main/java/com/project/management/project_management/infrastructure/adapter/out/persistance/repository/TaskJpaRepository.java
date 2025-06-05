package com.project.management.project_management.infrastructure.adapter.out.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.TaskEntity;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {

  // Aquí puedes agregar métodos personalizados si es necesario
  // Por ejemplo, para buscar tareas por título, estado o prioridad
  // List<TaskEntity> findByTitle(String title);
  // List<TaskEntity> findByStatus(TaskStatus status);
  // List<TaskEntity> findByPriority(TaskPriority priority);

}