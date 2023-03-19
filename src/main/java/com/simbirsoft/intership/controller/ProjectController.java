package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.dto.request.UpdatingProjectDto;
import com.simbirsoft.intership.dto.response.ProjectDto;
import com.simbirsoft.intership.dto.response.UserWithPermissionDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Project API")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "Получить список проектов пользователя")
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjectsOfUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.getProjectsOfUser(user));
    }

    @Operation(summary = "Получить список участников")
    @GetMapping("/{id}")
    public ResponseEntity<List<UserWithPermissionDto>> getMemberOfProject(@AuthenticationPrincipal User user,
                                                                          @PathVariable("id") long id) {
        return ResponseEntity.ok(projectService.getUsersOfProject(id, user));
    }


    @Operation(summary = "Создать проект")
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody @Valid CreatingProjectDto project,
                                                    @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.createProject(project, user));
    }

    @Operation(summary = "Обновить проект")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody @Valid UpdatingProjectDto projectDto,
                                                    @PathVariable("id") long id,
                                                    @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.updateProject(id, projectDto, user));
    }

    @Operation(summary = "Завершить проект", description = "Проект нельзя возобновить")
    @PostMapping("/{id}/close")
    public ResponseEntity closeProject(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        projectService.closeProject(id, user);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Пригласить в проект пользователя",
            description = "При этом необходимо выбрать права пользователя в проекте")
    @PostMapping("/{id}/member")
    public ResponseEntity<UserWithPermissionDto> inviteUser(@PathVariable("id") long id, InvitingUserDto invitingUserDto,
                                                            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.inviteUser(id, invitingUserDto, user));
    }

    @Operation(summary = "Удалить пользователя из проекта")
    @DeleteMapping("/{id}/member/{memberId}")
    public ResponseEntity removeUser(@PathVariable("id") long id, @PathVariable("memberId") long memberId,
                                     @AuthenticationPrincipal User user) {
        projectService.deleteUser(id, memberId, user);
        return ResponseEntity.noContent().build();
    }
}
