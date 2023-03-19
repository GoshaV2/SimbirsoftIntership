package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.dto.response.TaskDto;
import com.simbirsoft.intership.exception.NotFoundPermissionException;
import com.simbirsoft.intership.exception.NotFoundTaskException;
import com.simbirsoft.intership.exception.ProjectClosedException;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import com.simbirsoft.intership.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ProjectPermissionService projectPermissionService;
    private final RealiseService realiseService;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public TaskDto createTask(CreatingTaskDto creatingTaskDto, User user) {
        ProjectPermission permissionLeading = projectPermissionService
                .getPermission(user.getId(), creatingTaskDto.getProjectId(), Permission.Leading);
        if (permissionLeading.getProject().getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(creatingTaskDto.getProjectId(), user.getId());
        }
        ProjectPermission permissionPerformer = projectPermissionService
                .getPermission(user.getId(), creatingTaskDto.getProjectId());
        Realise realise = realiseService.getRealise(creatingTaskDto.getRealiseId(),
                permissionLeading.getProject().getId());
        Task task = taskRepository.save(Task.builder()
                .owner(user)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .title(creatingTaskDto.getTitle())
                .performer(permissionPerformer.getUser())
                .owner(permissionLeading.getUser())
                .project(permissionLeading.getProject())
                .realise(realise)
                .build());
        return TaskDto.from(task, permissionLeading.getProject());
    }

    @Transactional
    @Override
    public TaskDto updateTask(long taskId, UpdatingTaskDto updatingTaskDto, User user) {
        ProjectPermission permissionOwner = projectPermissionService.getPermission(
                user.getId(),
                updatingTaskDto.getProjectId()
        );
        if (permissionOwner.getProject().getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(updatingTaskDto.getProjectId(), user.getId());
        }

        Task task = taskRepository.findTaskByIdAndProjectId(taskId, permissionOwner.getProject().getId())
                .orElseThrow(() -> new NotFoundTaskException(taskId, updatingTaskDto.getProjectId()));
        ProjectPermission permissionPerformer = projectPermissionService
                .getPermission(updatingTaskDto.getPerformerId(), updatingTaskDto.getProjectId());
        Realise realise = realiseService.getRealise(updatingTaskDto.getRealiseId(),
                permissionOwner.getProject().getId());
        task.setRealise(realise);
        task.setPerformer(permissionPerformer.getUser());
        task.setTitle(updatingTaskDto.getTitle());

        return TaskDto.from(taskRepository.save(task), permissionOwner.getProject());
    }

    @Transactional
    @Override
    public void changeTaskStatus(long taskId, ChangeTaskStatusDto changeTaskStatusDto, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(
                user.getId(),
                changeTaskStatusDto.getProjectId()
        );
        if (projectPermission.getProject().getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(changeTaskStatusDto.getProjectId(), user.getId());
        }
        Task task = taskRepository.findTaskByIdAndProjectId(taskId, projectPermission.getProject().getId())
                .orElseThrow(() -> new NotFoundTaskException(taskId, changeTaskStatusDto.getProjectId()));

        hasAccessChangeStatusTask(projectPermission, task);

        task.setTaskStatus(changeTaskStatusDto.getTaskStatus());
        taskRepository.save(task);
    }

    private void hasAccessChangeStatusTask(ProjectPermission projectPermission, Task task) {
        //статус может менять пользователь Leading, испольняющий и владелец проекта
        if (!projectPermission.getPermissions().contains(Permission.Leading) &&
                !task.getPerformer().getId().equals(projectPermission.getUser().getId())) {
            throw new NotFoundPermissionException(task.getPerformer().getId(), projectPermission.getProject().getId());
        }
    }

    @Transactional
    @Override
    public void deleteTask(long taskId, long projectId, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(
                user.getId(),
                projectId,
                Permission.Leading
        );
        if (projectPermission.getProject().getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(projectId, user.getId());
        }
        Task task = taskRepository.findTaskByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new NotFoundTaskException(taskId, projectId));
        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskDto> getTaskOfProject(long projectId, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(user.getId(), projectId);
        return taskRepository.findAllByProject(projectPermission.getProject()).stream()
                .map(task -> TaskDto.from(task, projectPermission.getProject()))
                .toList();
    }
}
