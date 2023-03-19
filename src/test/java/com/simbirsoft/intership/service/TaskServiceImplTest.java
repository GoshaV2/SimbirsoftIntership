package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.exception.NotFoundPermissionException;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import com.simbirsoft.intership.repository.TaskRepository;
import com.simbirsoft.intership.service.constants.ProjectPermissions;
import com.simbirsoft.intership.service.constants.Tasks;
import com.simbirsoft.intership.service.constants.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private ProjectPermissionService projectPermissionService;
    @Mock
    private RealiseService realiseService;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void changeTaskStatus_WhenOwnerAndLeadingAndUsualAndOtherUsual_NotThrowsAndThrowException() {
        when(projectPermissionService.getPermission(1,1))
                .thenReturn(ProjectPermissions.Lead.getProjectPermission());
        when(projectPermissionService.getPermission(2,1))
                .thenReturn(ProjectPermissions.Usual.getProjectPermission());
        when(projectPermissionService.getPermission(3,1))
                .thenReturn(ProjectPermissions.LeadingNotAdmin.getProjectPermission());
        when(projectPermissionService.getPermission(4,1))
                .thenReturn(ProjectPermissions.OtherUsual.getProjectPermission());
        when(taskRepository.findTaskByIdAndProjectId(1,1))
                .thenReturn(Optional.ofNullable(Tasks.TaskInProgress.getTask()));
        ChangeTaskStatusDto changeTaskStatusDto=new ChangeTaskStatusDto();
        changeTaskStatusDto.setTaskStatus(TaskStatus.IN_PROGRESS);
        changeTaskStatusDto.setProjectId(1);
        assertDoesNotThrow(()->{
            taskService.changeTaskStatus(1,changeTaskStatusDto, Users.User1.getUser());
        });
        assertDoesNotThrow(()->{
            taskService.changeTaskStatus(1,changeTaskStatusDto, Users.User2.getUser());
        });
        assertDoesNotThrow(()->{
            taskService.changeTaskStatus(1,changeTaskStatusDto, Users.User3.getUser());
        });
        assertThrows(NotFoundPermissionException.class,()->{
            taskService.changeTaskStatus(1,changeTaskStatusDto,Users.User4.getUser());
        });

    }
}