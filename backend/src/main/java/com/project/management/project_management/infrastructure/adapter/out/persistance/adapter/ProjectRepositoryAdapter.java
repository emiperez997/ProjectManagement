package com.project.management.project_management.infrastructure.adapter.out.persistance.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.management.project_management.domain.model.Project;
import com.project.management.project_management.domain.port.out.ProjectRepositoryPort;
import com.project.management.project_management.infrastructure.adapter.out.mapper.ProjectMapper;
import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.ProjectEntity;
import com.project.management.project_management.infrastructure.adapter.out.persistance.repository.ProjectJpaRepository;

@Service
public class ProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectMapper projectMapper;

    public ProjectRepositoryAdapter(ProjectJpaRepository projectJpaRepository, ProjectMapper projectMapper) {
        this.projectJpaRepository = projectJpaRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity projectEntity = projectMapper.toEntity(project);
        System.out.println("Saving project: " + projectEntity);
        ProjectEntity savedEntity = projectJpaRepository.save(projectEntity);
        return projectMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id)
                .map(projectMapper::toDomain);
    }

    @Override
    public List<Project> findByOwnerId(Long ownerId) {
        return projectJpaRepository.findByOwnerId(ownerId)
                .stream()
                .map(projectMapper::toDomain)
                .toList();
    }

    @Override
    public List<Project> findByMemberId(Long memberId) {
        return projectJpaRepository.findByMembersId(memberId)
                .stream()
                .map(projectMapper::toDomain)
                .toList();

    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdAndOwnerId(Long projectId, Long ownerId) {
        return projectJpaRepository.existsByIdAndOwnerId(projectId, ownerId);
    }

    @Override
    public boolean existsByIdAndMemberId(Long projectId, Long memberId) {
        return projectJpaRepository.existsByIdAndMembersId(projectId, memberId);
    }

}
