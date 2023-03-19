package com.simbirsoft.intership.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

/**
 * Сервис генерации и парсинга JWT токена
 */
public interface JwtService {
    /**
     * Извлечь логин пользователя
     *
     * @param token токен
     * @return логин
     */
    String extractUsername(String token);

    /**
     * Генерация токена
     *
     * @param userDetails информация о пользователе
     * @return токен
     */
    String generateToken(UserDetails userDetails);

    /**
     * Получить доп параметры из токена
     *
     * @param token          токен
     * @param claimsResolver функция
     * @param <T>            тип возвращаемого параметра
     * @return параметр
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);


    /**
     * Генерация токена с доп параметрами
     *
     * @param extraClaims доп параметры
     * @param userDetails информация о пользователе
     * @return токен
     */
    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );


    /**
     * Проверка токена на валидность
     *
     * @param token       токен
     * @param userDetails информация о пользователе
     * @return информация о валидности токена
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
