package com.simbirsoft.intership.dto.response;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ProjectDto {
    private Long id;
    private UserDto owner;
    private String title;
    private ProjectStatus projectStatus;

    public static ProjectDto from(Project project, User owner) {
        return ProjectDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .projectStatus(project.getProjectStatus())
                .owner(UserDto.from(owner))
                .build();
    }
}
