package com.simbirsoft.intership.service;

import com.simbirsoft.intership.exception.AlreadyHasPermissionException;
import com.simbirsoft.intership.exception.NotFoundPermissionException;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.repository.ProjectPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjectPermissionServiceImpl implements ProjectPermissionService {
    private final ProjectPermissionRepository projectPermissionRepository;

    @Override
    public ProjectPermission getPermission(long userId, long projectId) {
        return projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new NotFoundPermissionException(userId, projectId));
    }

    @Override
    public ProjectPermission addPermission(User user, Project project, Permission permission) {
        hasNotPermission(user.getId(), project.getId());
        return projectPermissionRepository.save(ProjectPermission.builder()
                .user(user)
                .project(project)
                .permissions(Stream.of(permission)
                        .collect(Collectors.toSet()))
                .build());
    }

    @Override
    public ProjectPermission getPermission(long userId, long projectId, Permission permission) {
        ProjectPermission projectPermission = getPermission(userId, projectId);
        if (!projectPermission.getPermissions().contains(permission)) {
            throw new NotFoundPermissionException(userId, projectId);
        }
        return projectPermission;
    }

    @Override
    @Transactional
    public void deletePermission(User user, Project project) {
        ProjectPermission projectPermission = getPermission(user.getId(), project.getId());
        projectPermissionRepository.delete(projectPermission);
    }

    @Override
    public List<ProjectPermission> getAllProjectPermissionOfUser(User user) {
        return projectPermissionRepository.findAllByUser(user);
    }

    @Override
    public List<ProjectPermission> getAllProjectPermissionOfProject(long projectId, User user) {
        ProjectPermission projectPermission = getPermission(user.getId(), projectId);
        return projectPermissionRepository.findAllByProject(projectPermission.getProject());
    }

    private void hasNotPermission(long userId, long projectId) {
        if (projectPermissionRepository.existsByUserIdAndProjectId(userId, projectId)) {
            throw new AlreadyHasPermissionException(projectId, userId);
        }
    }

}
