package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;

public interface ProjectService {
    Project createProject(CreatingProjectDto project, User user);

    Project findProjectForOwner(long projectId, User user);
    Project findProjectById(long projectId);
}

