package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectById(long id);

}
