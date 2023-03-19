package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.response.UserDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.security.auth.AuthenticationRequest;
import com.simbirsoft.intership.security.auth.AuthenticationResponse;
import com.simbirsoft.intership.security.auth.RegisterRequest;
import com.simbirsoft.intership.security.service.AuthenticationService;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Получить авторизированного пользователя")
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserDto.from(user));
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(examples = {
                    @ExampleObject(name = "Пример",
                            value = """
                                    {
                                      "fullName": "Иванов Иван Иванович",
                                      "email": "test@mail.ru",
                                      "password": "1234567890"
                                    }""")}
            ))
                                                           @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Аутентификация")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticated(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(examples = {
                    @ExampleObject(name = "Пример",
                            value = """
                                    {
                                      "email": "ivanov@mail.ru",
                                      "password": "1234567890"
                                    }""")}
            ))
                                                                @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
