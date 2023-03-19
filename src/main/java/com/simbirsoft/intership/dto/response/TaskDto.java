package com.simbirsoft.intership.dto.response;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TaskDto {
    private Long id;
    private String title;
    private TaskStatus taskStatus;
    private UserDto owner;
    private UserDto performer;
    private RealiseDto realise;

    public static TaskDto from(Task task, Realise realise, Project project) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .taskStatus(task.getTaskStatus())
                .owner(UserDto.from(task.getOwner()))
                .performer(UserDto.from(task.getPerformer()))
                .realise(RealiseDto.from(realise,project))
                .build();
    }
}
