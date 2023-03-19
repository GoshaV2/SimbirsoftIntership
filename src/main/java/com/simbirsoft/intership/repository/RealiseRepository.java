package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Realise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealiseRepository extends JpaRepository<Realise, Long> {
    Optional<Realise> findByIdAndProjectId(long id,long projectId);
    void deleteRealiseByIdAndProjectId(long id, long projectId);
}
