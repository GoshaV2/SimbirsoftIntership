package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.dto.response.UserDto;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.security.auth.AuthenticationRequest;
import com.simbirsoft.intership.security.auth.AuthenticationResponse;
import com.simbirsoft.intership.security.auth.RegisterRequest;
import com.simbirsoft.intership.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid
                                                           @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticated(@Valid
                                                                @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
