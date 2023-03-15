package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.repository.ProjectRepository;
import com.simbirsoft.intership.service.RealiseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realises")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RealiseController {
    private final RealiseService realiseService;
    private final ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity addRealise(CreatingRealiseDto realise, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(realiseService.createRealise(realise, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRealise(@PathVariable("id") Long id,@AuthenticationPrincipal User user) {
        return ResponseEntity.noContent().build();
    }
}
