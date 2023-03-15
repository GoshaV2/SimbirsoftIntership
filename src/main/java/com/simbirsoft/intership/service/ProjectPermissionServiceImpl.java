package com.simbirsoft.intership.service;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.repository.ProjectPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjectPermissionServiceImpl implements ProjectPermissionService {
    private final ProjectPermissionRepository projectPermissionRepository;

    @Override
    public ProjectPermission getPermission(long userId, long projectId) {
        //todo exception
        return projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(userId, projectId).orElseThrow();
    }

    @Override
    public ProjectPermission addPermission(User user, Project project, Permission permission) {
        return projectPermissionRepository.save(ProjectPermission.builder()
                .user(user)
                .project(project)
                .permissions(Stream.of(Permission.Leading, Permission.Usual)
                        .collect(Collectors.toSet()))
                .build());
    }

    @Override
    public ProjectPermission getPermission(long userId, long projectId, Permission permission) {
        ProjectPermission projectPermission=getPermission(userId,projectId);
        if(!projectPermission.getPermissions().contains(permission)){
            throw new IllegalArgumentException();//todo exception
        }
        return projectPermission;
    }
}
