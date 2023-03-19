package com.simbirsoft.intership.service;

import com.simbirsoft.intership.exception.AlreadyHasPermissionException;
import com.simbirsoft.intership.exception.NotFoundPermissionException;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.repository.ProjectPermissionRepository;
import com.simbirsoft.intership.service.constants.ProjectPermissions;
import com.simbirsoft.intership.service.constants.Projects;
import com.simbirsoft.intership.service.constants.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectPermissionServiceImplTest {

    @Mock
    private ProjectPermissionRepository projectPermissionRepository;
    @InjectMocks
    private ProjectPermissionServiceImpl projectPermissionService;

    @Test
    public void getAnyPermission_WhenProjectPermissionExistAndNotExist_NotThrowAndThrowException() {
        when(projectPermissionRepository
                .findProjectPermissionByUserIdAndProjectId(1, 1))
                .thenReturn(Optional.ofNullable(ProjectPermissions.Lead.getProjectPermission()));
        when(projectPermissionRepository
                .findProjectPermissionByUserIdAndProjectId(2, 2))
                .thenReturn(Optional.empty());
        assertDoesNotThrow(() -> {
            projectPermissionService.getPermission(1, 1);
        });
        assertThrows(NotFoundPermissionException.class,
                () -> {
                    projectPermissionService.getPermission(2, 2);
                });
    }

    @Test
    public void addPermission_WhenPermissionNotExistAndExist_NotThrowAndThrowException() {
        when(projectPermissionRepository.existsByUserIdAndProjectId(1, 1)).thenReturn(true);
        when(projectPermissionRepository.existsByUserIdAndProjectId(3, 1)).thenReturn(false);
        assertDoesNotThrow(() -> projectPermissionService
                .addPermission(Users.User3.getUser(), Projects.ProjectOpen.getProject(), Permission.Leading));
        assertThrows(AlreadyHasPermissionException.class, () ->
        {
            projectPermissionService
                    .addPermission(Users.User1.getUser(), Projects.ProjectOpen.getProject(), Permission.Leading);
        });
    }

    @Test
    public void getCorrectPermission_WhenProjectPermissionExistAndNotExist_NotThrowAndThrowException() {
        when(projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(1, 1))
                .thenReturn(Optional.of(ProjectPermissions.Lead.getProjectPermission()));
        when(projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(2, 1))
                .thenReturn(Optional.of(ProjectPermissions.Usual.getProjectPermission()));
        assertDoesNotThrow(() -> {
            projectPermissionService.getPermission(1, 1, Permission.Leading);
        });
        assertThrows(NotFoundPermissionException.class, () -> {
            projectPermissionService.getPermission(2, 1, Permission.Leading);
        });
    }

    @Test
    public void deletePermission_WhenExistAndNotExist_NotThrowAndThrowException() {
        when(projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(1,1))
                .thenReturn(Optional.ofNullable(ProjectPermissions.Lead.getProjectPermission()));
        when(projectPermissionRepository.findProjectPermissionByUserIdAndProjectId(3,1))
                .thenReturn(Optional.empty());
        projectPermissionService.deletePermission(Users.User1.getUser(), Projects.ProjectOpen.getProject());
        verify(projectPermissionRepository)
                .delete(ProjectPermissions.Lead.getProjectPermission());
        assertThrows(NotFoundPermissionException.class,()->{
            projectPermissionService.deletePermission(Users.User3.getUser(), Projects.ProjectOpen.getProject());
        });
    }
}