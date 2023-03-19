package com.simbirsoft.intership.service.constants;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;

public enum Projects {
    ProjectOpen(Project.builder()
            .id(1L).owner(Users.User1.getUser()).projectStatus(ProjectStatus.ACTIVE)
            .title("Открытый проект с незакончеными задачами").build()),
    ProjectOpenWithDoneTask(Project.builder()
            .id(3L).owner(Users.User1.getUser()).projectStatus(ProjectStatus.ACTIVE)
            .title("Открытый проект с закончеными задачами").build()),
    ProjectClosed(Project.builder()
            .id(2L).owner(Users.User1.getUser()).projectStatus(ProjectStatus.CLOSED)
            .title("Закрытый проект").build());
    private Project project;

    Projects(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
