package com.project.management.project_management.domain.port.in;

import java.time.LocalDateTime;
import java.util.List;

import com.project.management.project_management.domain.model.Task;

public interface TaskUseCase {

    Task createTask(String title, String description, Long projectId, Long createdById);

    Task updateTask(Long taskId, String title, String description, Task.TaskStatus status,
            Task.TaskPriority priority, LocalDateTime dueDate, Long userId);

    void deleteTask(Long taskId, Long userId);

    Task getTaskById(Long taskId, Long userId);

    List<Task> getProjectTasks(Long projectId, Long userId);

    Task assignTask(Long taskId, Long assigneeId, Long userId);

    List<Task> getUserAssignedTasks(Long userId);
}
