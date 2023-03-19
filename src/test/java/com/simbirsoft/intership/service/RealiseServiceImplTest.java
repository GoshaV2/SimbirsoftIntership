package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.exception.RealiseHasTaskException;
import com.simbirsoft.intership.exception.UncorrectedDatesException;
import com.simbirsoft.intership.repository.RealiseRepository;
import com.simbirsoft.intership.repository.TaskRepository;
import com.simbirsoft.intership.service.constants.Projects;
import com.simbirsoft.intership.service.constants.Realises;
import com.simbirsoft.intership.service.constants.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealiseServiceImplTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectService projectService;
    @Mock
    private RealiseRepository realiseRepository;
    @InjectMocks
    private RealiseServiceImpl realiseService;

    @Test
    public void createRealise_WhenDateIsCorrectedAndDateIsUncorrected_notThrowAndThrowException() {
        when(projectService.findProjectForOwner(1, Users.User1.getUser()))
                .thenReturn(Projects.ProjectOpen.getProject());
        when(realiseRepository.save(any())).thenReturn(Realises.RealiseWithoutTask.getRealise());
        CreatingRealiseDto correctedDate=CreatingRealiseDto.builder()
                .dateStart(Date.valueOf("2023-03-12"))
                .dateEnd(Date.valueOf("2023-03-12"))
                .title("Коректные даты")
                .projectId(1)
                .build();
        CreatingRealiseDto uncorrectedDate=CreatingRealiseDto.builder()
                .dateStart(Date.valueOf("2023-03-12"))
                .dateEnd(Date.valueOf("2023-03-11"))
                .title("некоректные даты")
                .projectId(1)
                .build();
        assertDoesNotThrow(()->{
            realiseService.createRealise(correctedDate,Users.User1.getUser());
        });
        assertThrows(UncorrectedDatesException.class,()->{
            realiseService.createRealise(uncorrectedDate,Users.User1.getUser());
        });
    }
    @Test
    public void deleteRealise_WhenRealiseHasTask_ThrowException(){
        when(projectService.findProjectForOwner(1,Users.User1.getUser()))
                .thenReturn(Projects.ProjectOpen.getProject());
        when(taskRepository.existsByRealiseId(1))
                .thenReturn(true);
        assertThrows(RealiseHasTaskException.class,()->{
            realiseService.deleteRealise(1,1,Users.User1.getUser());
        });
    }
}