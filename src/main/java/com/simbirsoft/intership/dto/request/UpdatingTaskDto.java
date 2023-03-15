package com.simbirsoft.intership.dto.request;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatingTaskDto {
    private String title;
    private long projectId;
    private long performerId;
    private long realiseId;
}
