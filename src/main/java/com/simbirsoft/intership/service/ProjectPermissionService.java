package com.simbirsoft.intership.service;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;

public interface ProjectPermissionService {

    ProjectPermission getPermission(long userId, long projectId);

    ProjectPermission addPermission(User user, Project project, Permission permission);

    ProjectPermission getPermission(long userId, long projectId, Permission permission);
}
