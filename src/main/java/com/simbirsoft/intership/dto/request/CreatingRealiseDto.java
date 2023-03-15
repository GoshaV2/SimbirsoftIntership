package com.simbirsoft.intership.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class CreatingRealiseDto {
    private String title;
    private long projectId;
    private Date dateStart;
    private Date dateEnd;
}
