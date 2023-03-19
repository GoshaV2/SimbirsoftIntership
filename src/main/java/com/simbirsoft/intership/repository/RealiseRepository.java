package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Realise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealiseRepository extends JpaRepository<Realise, Long> {
    Optional<Realise> findByIdAndProjectId(long id, long projectId);

    List<Realise> findAllByProject(Project project);
}
