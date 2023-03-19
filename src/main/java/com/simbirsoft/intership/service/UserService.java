package com.simbirsoft.intership.service;

import com.simbirsoft.intership.model.User;

/**
 * Сервис управлением пользователя
 */
public interface UserService {
    /**
     * @param userId id пользователя
     * @return пользователь
     * @throws com.simbirsoft.intership.exception.NotFoundUserException не найден пользователь
     */
    User getUser(long userId);
}
