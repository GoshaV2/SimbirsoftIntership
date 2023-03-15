package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectPermissionService projectPermissionService;

    @Override
    public Project createProject(CreatingProjectDto creatingProject, User user) {
        Project project = projectRepository.save(Project.builder()
                .owner(user)
                .title(creatingProject.getTitle())
                .build());
        projectPermissionService.addPermission(user, project, Permission.Leading);
        return project;
    }

    @Override
    public Project findProjectForOwner(long projectId, User user) {
        Project project = projectRepository.findProjectById(projectId)
                .orElseThrow(IllegalArgumentException::new);
        if (!project.getOwner().getId().equals(user.getId())) {
            //todo exception
            throw new IllegalArgumentException();
        }
        return project;
    }

    @Override
    public Project findProjectById(long projectId) {
        //todo exception
        return projectRepository.findProjectById(projectId).orElseThrow();
    }
}
