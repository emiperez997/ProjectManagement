package com.project.management.project_management.infrastructure.adapter.out.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.ProjectEntity;

@Component
public class ProjectMapper {

    private final UserMapper userMapper;

    public ProjectMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ProjectEntity toEntity(Project project) {
        if (project == null) {
            return null;
        }

        ProjectEntity entity = new ProjectEntity();
        entity.setId(project.getId());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setStartDate(project.getStartDate());
        entity.setEndDate(project.getEndDate());
        entity.setStatus(project.getStatus());
        entity.setCreatedAt(project.getCreatedAt());
        entity.setUpdatedAt(project.getUpdatedAt());

        // Convert owner if available
        if (project.getOwner() != null) {
            entity.setOwner(userMapper.toEntity(project.getOwner()));
        }

        return entity;
    }

    public Project toDomain(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        Project project = new Project();
        project.setId(entity.getId());
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStartDate(entity.getStartDate());
        project.setEndDate(entity.getEndDate());
        project.setStatus(entity.getStatus());
        project.setCreatedAt(entity.getCreatedAt());
        project.setUpdatedAt(entity.getUpdatedAt());

        // Convert owner if available
        if (entity.getOwner() != null) {
            project.setOwner(userMapper.toDomain(entity.getOwner()));
        }

        return project;
    }

    public Project toDomainWithoutOwner(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        Project project = new Project();
        project.setId(entity.getId());
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStartDate(entity.getStartDate());
        project.setEndDate(entity.getEndDate());
        project.setStatus(entity.getStatus());
        project.setCreatedAt(entity.getCreatedAt());
        project.setUpdatedAt(entity.getUpdatedAt());

        // Do not set owner to avoid circular reference
        return project;
    }

    public ProjectEntity toEntityWithoutOwner(Project project) {
        if (project == null) {
            return null;
        }

        ProjectEntity entity = new ProjectEntity();
        entity.setId(project.getId());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setStartDate(project.getStartDate());
        entity.setEndDate(project.getEndDate());
        entity.setStatus(project.getStatus());
        entity.setCreatedAt(project.getCreatedAt());
        entity.setUpdatedAt(project.getUpdatedAt());

        // Do not set owner to avoid circular reference
        return entity;
    }

    public ProjectEntity toEntityWithoutRelationships(Project project) {
        if (project == null) {
            return null;
        }

        ProjectEntity entity = new ProjectEntity();
        entity.setId(project.getId());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setStartDate(project.getStartDate());
        entity.setEndDate(project.getEndDate());
        entity.setStatus(project.getStatus());
        entity.setCreatedAt(project.getCreatedAt());
        entity.setUpdatedAt(project.getUpdatedAt());

        // Do not set owner or any relationships to avoid circular references
        return entity;
    }

    public ProjectEntity toEntityWithRelationships(Project project) {
        if (project == null) {
            return null;
        }

        ProjectEntity entity = new ProjectEntity();
        entity.setId(project.getId());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setStartDate(project.getStartDate());
        entity.setEndDate(project.getEndDate());
        entity.setStatus(project.getStatus());
        entity.setCreatedAt(project.getCreatedAt());
        entity.setUpdatedAt(project.getUpdatedAt());

        // Convert owner if available
        if (project.getOwner() != null) {
            entity.setOwner(userMapper.toEntity(project.getOwner()));
        }

        // Relationships can be added here if needed
        return entity;
    }

    public List<Project> toDomainList(List<ProjectEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<ProjectEntity> toEntityList(List<Project> projects) {
        if (projects == null) {
            return null;
        }

        return projects.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
