package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.dto.response.RealiseDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.service.RealiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realises")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Realise API")
public class RealiseController {
    private final RealiseService realiseService;

    @Operation(summary = "Получить релизы в проекте")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<RealiseDto>> getRealisesOfProject(@PathVariable("projectId") long projectId,
                                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(realiseService.getRealisesOfProject(projectId, user));
    }

    @Operation(summary = "Добавить релиз")
    @PostMapping
    public ResponseEntity<RealiseDto> addRealise(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(examples = {
                    @ExampleObject(name = "Пример",
                            value = """
                                    {
                                      "title": "Название релиза(спринта)",
                                      "projectId": 0,
                                      "dateStart": "2023-03-19",
                                      "dateEnd": "2023-03-25"
                                    }""")}
            ))
                                                 @RequestBody @Valid CreatingRealiseDto realise,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(realiseService.createRealise(realise, user));
    }

    @Operation(summary = "Удалить релиз")
    @DeleteMapping("/{id}/project/{projectId}")
    public ResponseEntity deleteRealise(@PathVariable("id") Long id, @PathVariable("projectId") long projectId,
                                        @AuthenticationPrincipal User user) {
        realiseService.deleteRealise(id, projectId, user);
        return ResponseEntity.noContent().build();
    }
}
