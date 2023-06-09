package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProjectPermissionRepository extends JpaRepository<ProjectPermission, Long> {

    Optional<ProjectPermission> findProjectPermissionByUserIdAndProjectId(long userId, long projectId);

    boolean existsByUserIdAndProjectId(long userId, long projectId);

    List<ProjectPermission> findAllByUser(User user);

    List<ProjectPermission> findAllByProject(Project project);
}
