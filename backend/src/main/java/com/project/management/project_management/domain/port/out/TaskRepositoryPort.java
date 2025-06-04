package com.project.management.project_management.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.project.management.project_management.domain.model.Task;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByCreatedById(Long createdById);

    void deleteById(Long id);

    boolean existsByIdAndProjectOwnerId(Long taskId, Long ownerId);

    boolean existsByIdAndProjectMemberId(Long taskId, Long memberId);
}
