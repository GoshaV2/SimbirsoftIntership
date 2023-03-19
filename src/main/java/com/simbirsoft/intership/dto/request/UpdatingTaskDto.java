package com.simbirsoft.intership.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatingTaskDto {
    @Size(min = 10, max = 100)
    private String title;
    private long projectId;
    private long performerId;
    private long realiseId;
}
