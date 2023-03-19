package com.simbirsoft.intership.dto.response;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@SuperBuilder
public class RealiseDto {
    private Long id;
    private String title;
    private long projectId;
    private Date dateStart;
    private Date dateEnd;

    public static RealiseDto from(Realise realise, Project project) {
        return RealiseDto.builder()
                .id(realise.getId())
                .title(realise.getTitle())
                .projectId(project.getId())
                .dateStart(realise.getDateStart())
                .dateEnd(realise.getDateEnd())
                .build();
    }
}
