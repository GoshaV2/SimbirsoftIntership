package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.ProjectPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectPermissionRepository extends JpaRepository<ProjectPermission, Long> {

    Optional<ProjectPermission> findProjectPermissionByUserIdAndProjectId(long userId, long projectId);

}
