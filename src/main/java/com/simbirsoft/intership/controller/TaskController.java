package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.dto.response.TaskDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task API")
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Получить задачи проекта")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskDto>> getTasksOfProject(@PathVariable(name = "projectId") long projectId,
                                                           @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getTaskOfProject(projectId, user));
    }

    @Operation(summary = "Создать задачу", description = "Добавить задачу может либо владелец проекта либо тимлид")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid CreatingTaskDto creatingTaskDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.createTask(creatingTaskDto, user));
    }

    @Operation(summary = "Обновить задачу")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody @Valid UpdatingTaskDto task, @PathVariable(name = "id") long id,
                                              @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.updateTask(id, task, user));
    }

    @Operation(summary = "Сменить статус задачи",
            description = "Обновить статус может владелец проекта, любой тимлид или исполняющий задачу пользователь")
    @PatchMapping("/{id}")
    public ResponseEntity changeTaskStatus(@RequestBody @Valid ChangeTaskStatusDto changeTaskStatusDto,
                                           @PathVariable(name = "id") long id,
                                           @AuthenticationPrincipal User user) {
        taskService.changeTaskStatus(id, changeTaskStatusDto, user);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить задачу")
    @DeleteMapping("/{id}/project/{projectId}")
    public ResponseEntity deleteTask(@PathVariable(name = "id") long id, @PathVariable("projectId") long projectId,
                                     @AuthenticationPrincipal User user) {
        taskService.deleteTask(id, projectId, user);
        return ResponseEntity.noContent().build();
    }
}
