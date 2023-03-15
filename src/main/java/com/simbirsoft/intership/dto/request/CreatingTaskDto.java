package com.simbirsoft.intership.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatingTaskDto {
    private String title;
    private long projectId;
    private long performerId;
    private long realiseId;
}
