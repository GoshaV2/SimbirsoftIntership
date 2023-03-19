package com.simbirsoft.intership.security.service;

import com.simbirsoft.intership.exception.UserAlreadyExistException;
import com.simbirsoft.intership.security.auth.AuthenticationRequest;
import com.simbirsoft.intership.security.auth.AuthenticationResponse;
import com.simbirsoft.intership.security.auth.RegisterRequest;

/**
 * Сервис аутентификации
 */
public interface AuthenticationService {
    /**
     * Регистрация
     *
     * @param request модель с данными
     * @return токен
     * @throws UserAlreadyExistException пользователь с такоей почтой уже есть
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Аутентификация
     *
     * @param request модель с данными
     * @return токен
     * @throws org.springframework.security.core.AuthenticationException ошибка авторизации
     * @throws com.simbirsoft.intership.exception.NotFoundUserException  не найден пользователь
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
