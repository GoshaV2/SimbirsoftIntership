package com.simbirsoft.intership.dto.request;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatingTaskDto {
    @Size(min = 10,max = 100)
    private String title;
    private long projectId;
    private long performerId;
    private long realiseId;
}
