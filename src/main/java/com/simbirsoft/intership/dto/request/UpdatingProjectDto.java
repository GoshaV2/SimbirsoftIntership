package com.simbirsoft.intership.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatingProjectDto {
    @Size(min = 10,max = 100)
    private String title;
}
