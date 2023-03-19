package com.simbirsoft.intership.repository;

import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByIdAndProjectId(long taskId, long projectId);

    boolean existsByRealiseId(long realiseId);

    @Query("""
            select case when count(t)=0 then true else false end
            from Task t where t.project=:project and (t.taskStatus='BACKLOG' or t.taskStatus='IN_PROGRESS')
            """)
    boolean tasksIsDone(@Parameter(name = "project") Project project);

    boolean existsByOwnerOrPerformer(User owner, User performer);

    List<Task> findAllByProject(Project project);
}
