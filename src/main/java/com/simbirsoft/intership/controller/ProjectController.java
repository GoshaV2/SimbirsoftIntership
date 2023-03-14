package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingProject;
import com.simbirsoft.intership.dto.request.InvitingUser;
import com.simbirsoft.intership.dto.request.UpdatingProject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @PostMapping
    public ResponseEntity createProject(@RequestBody CreatingProject project){
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity updateProject(@RequestBody UpdatingProject project){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/invite")
    public ResponseEntity inviteUser(@RequestBody InvitingUser user,@PathVariable("id") long id){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/removeMember/{id}")
    public ResponseEntity removeUser(@RequestBody InvitingUser user, @PathVariable("id") long id){
        return ResponseEntity.ok().build();
    }
}
