package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.dto.request.UpdatingProjectDto;
import com.simbirsoft.intership.dto.response.ProjectDto;
import com.simbirsoft.intership.dto.response.UserWithPermissionDto;
import com.simbirsoft.intership.exception.*;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import com.simbirsoft.intership.repository.ProjectRepository;
import com.simbirsoft.intership.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectPermissionService projectPermissionService;
    private final UserService userService;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public ProjectDto createProject(CreatingProjectDto creatingProject, User user) {
        Project project = projectRepository.save(Project.builder()
                .owner(user)
                .title(creatingProject.getTitle())
                .projectStatus(ProjectStatus.ACTIVE)
                .build());
        projectPermissionService.addPermission(user, project, Permission.Leading);
        return ProjectDto.from(project, user);
    }

    @Override
    public Project findProjectForOwner(long projectId, User user) {
        Project project = projectRepository.findProjectByIdAndOwner(projectId, user)
                .orElseThrow(() -> new NotFoundProjectException(projectId, user.getId()));
        return project;
    }

    @Transactional
    @Override
    public ProjectDto updateProject(long projectId, UpdatingProjectDto updatingProjectDto, User user) {
        Project project = findProjectForOwner(projectId, user);
        project.setTitle(updatingProjectDto.getTitle());
        return ProjectDto.from(projectRepository.save(project), user);
    }

    @Override
    public void closeProject(long projectId, User user) {
        Project project = findProjectForOwner(projectId, user);
        if(project.getProjectStatus()==ProjectStatus.CLOSED){
            throw new ProjectClosedException(projectId,user.getId());
        }
        if (!taskRepository.tasksIsDone(project)) {
            throw new TaskNotDoneException(projectId);
        }
        project.setProjectStatus(ProjectStatus.CLOSED);
        projectRepository.save(project);
    }

    @Transactional
    @Override
    public UserWithPermissionDto inviteUser(long projectId, InvitingUserDto invitingUserDto, User user) {
        Project project = findProjectForOwner(projectId, user);
        User member = userService.getUser(invitingUserDto.getUserId());
        ProjectPermission projectPermission = projectPermissionService
                .addPermission(member, project, invitingUserDto.getPermission());
        return UserWithPermissionDto.from(projectPermission);
    }

    @Transactional
    @Override
    public void deleteUser(long projectId, long memberId, User user) {
        if (memberId == user.getId()) {
            throw new OwnerDeletingException(projectId, user.getId());
        }
        Project project = findProjectForOwner(projectId, user);
        User member = userService.getUser(memberId);
        if (taskRepository.existsByOwnerOrPerformer(member, member)) {
            throw new UserHasTaskException(memberId);
        }
        projectPermissionService.deletePermission(member, project);
    }
}
