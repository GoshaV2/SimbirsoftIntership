package com.simbirsoft.intership.security.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 5, max = 70)
    private String fullName;
    @Email
    @Size(max = 250)
    private String email;
    @Size(min = 8, max = 250)
    private String password;
}
