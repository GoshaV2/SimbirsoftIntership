package com.simbirsoft.intership.dto.request;

import com.simbirsoft.intership.model.enumaration.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeTaskStatusDto {
    private long projectId;
    private TaskStatus taskStatus;
}
