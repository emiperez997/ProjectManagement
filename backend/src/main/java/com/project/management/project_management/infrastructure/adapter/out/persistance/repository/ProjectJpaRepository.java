package com.project.management.project_management.infrastructure.adapter.out.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.ProjectEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findByOwnerId(Long ownerId);

    List<ProjectEntity> findByMembersId(Long memberId);

    boolean existsByIdAndOwnerId(Long projectId, Long ownerId);

    boolean existsByIdAndMembersId(Long projectId, Long memberId);

}
