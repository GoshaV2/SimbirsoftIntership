package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.dto.request.UpdatingProjectDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public ResponseEntity createProject(@RequestBody CreatingProjectDto project, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.createProject(project,user));
    }

    @PutMapping
    public ResponseEntity updateProject(@RequestBody UpdatingProjectDto project) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity inviteUser(@RequestBody InvitingUserDto user, @PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeMember/{id}")
    public ResponseEntity removeUser(@RequestBody InvitingUserDto user, @PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }
}
