package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody CreatingTaskDto creatingTaskDto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.createTask(creatingTaskDto, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody UpdatingTaskDto task, @PathVariable(name = "id") long id,
                                     @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.updateTask(id, task, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeTaskStatus(@RequestBody ChangeTaskStatusDto changeTaskStatusDto,
                                           @PathVariable(name = "id") long id,
                                           @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.changeTaskStatus(id, changeTaskStatusDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable(name = "id") long id, @RequestBody long projectId,
                                     @AuthenticationPrincipal User user) {
        taskService.deleteTask(id, projectId, user);
        return ResponseEntity.noContent().build();
    }
}
