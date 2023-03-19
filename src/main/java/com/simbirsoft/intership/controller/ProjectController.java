package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.dto.request.UpdatingProjectDto;
import com.simbirsoft.intership.dto.response.ProjectDto;
import com.simbirsoft.intership.dto.response.UserWithPermissionDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody @Valid CreatingProjectDto project,
                                                    @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.createProject(project, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody @Valid UpdatingProjectDto projectDto,
                                                    @PathVariable("id") long id,
                                                    @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.updateProject(id, projectDto, user));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity closeProject(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        projectService.closeProject(id, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/member")
    public ResponseEntity<UserWithPermissionDto> inviteUser(@PathVariable("id") long id, InvitingUserDto invitingUserDto,
                                                            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.inviteUser(id, invitingUserDto, user));
    }

    @DeleteMapping("/{id}/member/{memberId}")
    public ResponseEntity removeUser(@PathVariable("id") long id, @PathVariable("memberId") long memberId,
                                     @AuthenticationPrincipal User user) {
        projectService.deleteUser(id, memberId, user);
        return ResponseEntity.noContent().build();
    }
}
