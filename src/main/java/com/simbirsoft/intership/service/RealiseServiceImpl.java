package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.dto.response.RealiseDto;
import com.simbirsoft.intership.exception.NotFoundRealiseException;
import com.simbirsoft.intership.exception.ProjectClosedException;
import com.simbirsoft.intership.exception.RealiseHasTaskException;
import com.simbirsoft.intership.exception.UncorrectedDatesException;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import com.simbirsoft.intership.repository.RealiseRepository;
import com.simbirsoft.intership.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealiseServiceImpl implements RealiseService {
    private final ProjectService projectService;
    private final RealiseRepository realiseRepository;
    private final TaskRepository taskRepository;

    private final ProjectPermissionService projectPermissionService;

    @Transactional(readOnly = true)
    @Override
    public List<RealiseDto> getRealisesOfProject(long projectId, User user) {
        ProjectPermission projectPermission = projectPermissionService.getPermission(user.getId(), projectId);
        return realiseRepository.findAllByProject(projectPermission.getProject()).stream()
                .map(realise -> RealiseDto.from(realise, projectPermission.getProject()))
                .toList();
    }

    @Transactional
    @Override
    public RealiseDto createRealise(CreatingRealiseDto creatingRealiseDto, User user) {
        isCorrectDates(creatingRealiseDto.getDateStart(), creatingRealiseDto.getDateEnd());
        Project project = projectService.findProjectForOwner(creatingRealiseDto.getProjectId(), user);
        if (project.getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(project.getId(), user.getId());
        }
        Realise realise = realiseRepository.save(Realise.builder()
                .title(creatingRealiseDto.getTitle())
                .project(project)
                .dateStart(creatingRealiseDto.getDateStart())
                .dateEnd(creatingRealiseDto.getDateEnd())
                .build());
        return RealiseDto.from(realise, project);
    }

    @Override
    public Realise getRealise(long realiseId, long projectId) {
        return realiseRepository.findByIdAndProjectId(realiseId, projectId)
                .orElseThrow(() -> new NotFoundRealiseException(realiseId, projectId));
    }

    @Transactional
    @Override
    public void deleteRealise(long realiseId, long projectId, User user) {
        //проект получаем, чтобы удостовериться это админ или нет
        Project project = projectService.findProjectForOwner(projectId, user);
        if (project.getProjectStatus() == ProjectStatus.CLOSED) {
            throw new ProjectClosedException(projectId, user.getId());
        }
        if (taskRepository.existsByRealiseId(realiseId)) {
            throw new RealiseHasTaskException(realiseId);
        }
        Realise realise = getRealise(realiseId, project.getId());
        realiseRepository.delete(realise);
    }

    private void isCorrectDates(Date dateStart, Date dateEnd) {
        if (dateEnd.before(dateStart)) {
            throw new UncorrectedDatesException(dateStart, dateEnd);
        }
    }
}
