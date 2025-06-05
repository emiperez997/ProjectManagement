package com.project.management.project_management.infrastructure.adapter.out.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.ProjectEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {

  // Aquí puedes agregar métodos personalizados si es necesario
  // Por ejemplo, para buscar proyectos por nombre o estado
  // List<ProjectEntity> findByName(String name);
  // List<ProjectEntity> findByStatus(ProjectStatus status);

}
