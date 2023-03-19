package com.simbirsoft.intership.security.service;

import com.simbirsoft.intership.security.auth.AuthenticationRequest;
import com.simbirsoft.intership.security.auth.AuthenticationResponse;
import com.simbirsoft.intership.security.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
