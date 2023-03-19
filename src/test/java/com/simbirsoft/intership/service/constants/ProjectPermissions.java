package com.simbirsoft.intership.service.constants;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.enumaration.Permission;

import java.util.Set;

public enum ProjectPermissions {
    Lead(ProjectPermission.builder().id(1L).project(Projects.ProjectOpen.getProject())
            .permissions(Set.of(Permission.Leading)).user(Users.User1.getUser()).build()),
    Usual(ProjectPermission.builder().id(2L).project(Projects.ProjectOpen.getProject())
            .permissions(Set.of(Permission.Usual)).user(Users.User2.getUser()).build()),
    LeadingNotAdmin(ProjectPermission.builder().id(3L).project(Projects.ProjectOpen.getProject())
            .permissions(Set.of(Permission.Leading)).user(Users.User3.getUser()).build()),
    OtherUsual(ProjectPermission.builder().id(4L).project(Projects.ProjectOpen.getProject())
            .permissions(Set.of(Permission.Usual)).user(Users.User4.getUser()).build());
    private ProjectPermission projectPermission;

    ProjectPermissions(ProjectPermission projectPermission) {
        this.projectPermission = projectPermission;
    }

    public ProjectPermission getProjectPermission() {
        return projectPermission;
    }
}
