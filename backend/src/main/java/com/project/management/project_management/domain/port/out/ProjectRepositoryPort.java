package com.project.management.project_management.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.project.management.project_management.domain.model.Project;

public interface ProjectRepositoryPort {

    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findByOwnerId(Long ownerId);

    List<Project> findByMemberId(Long memberId);

    void deleteById(Long id);

    boolean existsByIdAndOwnerId(Long projectId, Long ownerId);

    boolean existsByIdAndMemberId(Long projectId, Long memberId);
}
