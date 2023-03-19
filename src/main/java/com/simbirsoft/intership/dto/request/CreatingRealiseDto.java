package com.simbirsoft.intership.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
@Getter
@Setter
@SuperBuilder
public class CreatingRealiseDto {
    @Size(min = 10,max = 100)
    private String title;
    private long projectId;
    private Date dateStart;
    private Date dateEnd;
}
