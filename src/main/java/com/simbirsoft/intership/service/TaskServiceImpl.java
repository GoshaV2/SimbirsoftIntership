package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import com.simbirsoft.intership.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ProjectPermissionService projectPermissionService;
    private final RealiseService realiseService;
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(CreatingTaskDto creatingTaskDto, User user) {
        ProjectPermission permissionLeading = projectPermissionService
                .getPermission(user.getId(), creatingTaskDto.getProjectId(), Permission.Leading);
        ProjectPermission permissionPerformer = projectPermissionService
                .getPermission(user.getId(), creatingTaskDto.getProjectId());
        Realise realise = realiseService.getRealise(creatingTaskDto.getRealiseId(),
                permissionLeading.getProject().getId());
        return taskRepository.save(Task.builder()
                .owner(user)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .title(creatingTaskDto.getTitle())
                .performer(permissionPerformer.getUser())
                .owner(permissionLeading.getUser())
                .project(permissionLeading.getProject())
                .realise(realise)
                .build());
    }

    @Override
    public Task updateTask(long taskId, UpdatingTaskDto updatingTaskDto, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(
                user.getId(),
                updatingTaskDto.getProjectId()
        );

        Task task = taskRepository.findTaskByIdAndProjectId(taskId, projectPermission.getProject().getId())
                .orElseThrow();//todo exception
        ProjectPermission permissionPerformer = projectPermissionService
                .getPermission(updatingTaskDto.getPerformerId(), updatingTaskDto.getProjectId());

        task.setRealise(realiseService.getRealise(updatingTaskDto.getRealiseId(),
                projectPermission.getProject().getId()));
        task.setPerformer(permissionPerformer.getUser());
        task.setTitle(updatingTaskDto.getTitle());

        return taskRepository.save(task);
    }

    @Override
    public Task changeTaskStatus(long taskId, ChangeTaskStatusDto changeTaskStatusDto, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(
                user.getId(),
                changeTaskStatusDto.getProjectId()
        );
        Task task = taskRepository.findTaskByIdAndProjectId(taskId, projectPermission.getProject().getId())
                .orElseThrow();//todo exception

        checkAccessChangeStatusTask(projectPermission, task);

        task.setTaskStatus(changeTaskStatusDto.getTaskStatus());
        return taskRepository.save(task);
    }

    private void checkAccessChangeStatusTask(ProjectPermission projectPermission, Task task) {
        if (!projectPermission.getPermissions().contains(Permission.Leading) &&
                !task.getPerformer().getId().equals(projectPermission.getUser().getId())) {
            throw new IllegalArgumentException(); //todo exception
        }
    }

    @Override
    public void deleteTask(long taskId, long projectId, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(
                user.getId(),
                projectId,
                Permission.Leading
        );
        //todo exception
        Task task = taskRepository.findTaskByIdAndProjectId(taskId, projectId).orElseThrow();
        taskRepository.delete(task);
    }
}
