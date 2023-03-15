package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.repository.RealiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealiseServiceImpl implements RealiseService {
    private final ProjectService projectService;
    private final RealiseRepository realiseRepository;

    @Override
    public Realise createRealise(CreatingRealiseDto realise, User user) {
        //todo сделать проверку на соотвествие времени, подумать нужна ли возможность сделать дату предудущих дней
        Project project = projectService.findProjectForOwner(realise.getProjectId(), user);
        return realiseRepository.save(Realise.builder()
                .title(realise.getTitle())
                .project(project)
                .dateStart(realise.getDateStart())
                .dateEnd(realise.getDateEnd())
                .build());
    }

    @Override
    public Realise getRealise(long id, long projectId) {
        //todo  exception
        return realiseRepository.findByIdAndProjectId(id,projectId).orElseThrow();
    }
}
