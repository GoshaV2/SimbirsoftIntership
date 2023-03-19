package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByIdAndOwner(long id, User owner);

}
