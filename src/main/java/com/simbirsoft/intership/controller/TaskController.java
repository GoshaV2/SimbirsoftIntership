package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingTask;
import com.simbirsoft.intership.dto.request.UpdatingTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @PostMapping
    public ResponseEntity createTask(@RequestBody CreatingTask task){
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity updateTask(@RequestBody UpdatingTask task){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable(name = "id") long id){
        return ResponseEntity.noContent().build();
    }
}
