package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.exception.*;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import com.simbirsoft.intership.repository.ProjectRepository;
import com.simbirsoft.intership.repository.TaskRepository;
import com.simbirsoft.intership.service.constants.Projects;
import com.simbirsoft.intership.service.constants.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectPermissionService projectPermissionService;
    @Mock
    private UserService userService;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    public void findProjectForOwner_WhenNotExist_ThrowException() {
        when(projectRepository.findProjectByIdAndOwner(1, Users.User3.getUser()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundProjectException.class, () -> {
            projectService.findProjectForOwner(1, Users.User3.getUser());
        });
    }

    @Test
    public void closeProject_WhenTasksDoneAndNotDoneAndProjectClosed_NotThrowAndThrowAndThrowException() {
        when(projectRepository.findProjectByIdAndOwner(3, Users.User1.getUser()))
                .thenReturn(Optional.ofNullable(Projects.ProjectOpenWithDoneTask.getProject()));
        when(projectRepository.findProjectByIdAndOwner(1, Users.User1.getUser()))
                .thenReturn(Optional.ofNullable(Projects.ProjectOpen.getProject()));
        when(projectRepository.findProjectByIdAndOwner(2, Users.User1.getUser()))
                .thenReturn(Optional.ofNullable(Projects.ProjectClosed.getProject()));
        when(taskRepository.tasksIsDone(Projects.ProjectOpenWithDoneTask.getProject()))
                .thenReturn(true);
        when(taskRepository.tasksIsDone(Projects.ProjectOpen.getProject()))
                .thenReturn(false);
        assertDoesNotThrow(() -> {
            projectService.closeProject(3, Users.User1.getUser());
        });
        assertThrows(TaskNotDoneException.class, () -> {
            projectService.closeProject(1, Users.User1.getUser());
        });
        assertThrows(ProjectClosedException.class, () -> {
            projectService.closeProject(2, Users.User1.getUser());
        });
        verify(projectRepository).save(Projects.ProjectOpenWithDoneTask.getProject());

        Projects.ProjectOpen.getProject().setProjectStatus(ProjectStatus.ACTIVE);
    }

    @Test
    public void inviteUser_WhenUserAlreadyExists_ThrowException() {
        when(projectRepository.findProjectByIdAndOwner(1, Users.User1.getUser()))
                .thenReturn(Optional.ofNullable(Projects.ProjectOpen.getProject()));
        when(userService.getUser(1)).thenReturn(Users.User1.getUser());
        when(projectPermissionService
                .addPermission(Users.User1.getUser(), Projects.ProjectOpen.getProject(), Permission.Leading))
                .thenThrow(new AlreadyHasPermissionException(Users.User1.getUser().getId(), 1));
        InvitingUserDto invitingUserDto = new InvitingUserDto();
        invitingUserDto.setUserId(1);
        invitingUserDto.setPermission(Permission.Leading);
        assertThrows(AlreadyHasPermissionException.class,

                () -> {
                    projectService.inviteUser(1, invitingUserDto, Users.User1.getUser());
                });
    }

    @Test
    public void deleteUser_WhenUserIsOwnerAndUserHasTask_ThrowsException() {
        when(projectRepository.findProjectByIdAndOwner(1, Users.User1.getUser()))
                .thenReturn(Optional.ofNullable(Projects.ProjectOpen.getProject()));
        when(taskRepository.existsByOwnerOrPerformer(Users.User2.getUser(), Users.User2.getUser()))
                .thenReturn(true);
        when(userService.getUser(2)).thenReturn(Users.User2.getUser());

        assertThrows(OwnerDeletingException.class, () ->
        {
            projectService.deleteUser(1, 1, Users.User1.getUser());
        });
        assertThrows(UserHasTaskException.class, () -> {
            projectService.deleteUser(1,2,Users.User1.getUser());
        });
    }


}