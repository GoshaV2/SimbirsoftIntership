package com.simbirsoft.intership.service;

import com.simbirsoft.intership.exception.AlreadyHasPermissionException;
import com.simbirsoft.intership.exception.NotFoundPermissionException;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;

import java.util.List;

/**
 * Сервис для проверки прав доступа пользователя к проекту.
 *
 * @ProjectPermission является связью между проектом и пользователем, а так же содержит права доступа
 */
public interface ProjectPermissionService {
    /**
     * Получить связь между пользователем и проектом, а так же его права
     *
     * @param userId    id пользователя
     * @param projectId id проекта
     * @return возвращает связь между проектом и пользоватем, а так же его права доступа
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException если не были найдены права доступа
     */
    ProjectPermission getPermission(long userId, long projectId);

    /**
     * Добавляет связь между пользоватем и проектом, а так же права пользователю
     *
     * @param user       пользователь
     * @param project    проект
     * @param permission право пользователя
     * @return - возвращает добавленную связь между проектом и пользоватем, а так же его права доступа
     * @throws AlreadyHasPermissionException связь между пользоватем и проектом уже существует
     */
    ProjectPermission addPermission(User user, Project project, Permission permission);

    /**
     * Получить связь между пользователем и проектом, а так же его права, если они есть
     *
     * @param userId     id пользователя
     * @param projectId  id проекта
     * @param permission права, которые должны содержать пользователь
     * @return возвращает связь между проектом и пользоватем, а так же его права доступа
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException если не были найдены права доступа
     */
    ProjectPermission getPermission(long userId, long projectId, Permission permission);

    /**
     * Удалить связь между пользователем и проектом и его права
     *
     * @param user    пользователь
     * @param project проект
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException если не были найдены права доступа
     */
    void deletePermission(User user, Project project);


    /**
     * Получить все возможные доступы пользователя
     *
     * @param user пользователь
     * @return все доступы к проектам
     */
    List<ProjectPermission> getAllProjectPermissionOfUser(User user);

    /**
     * Получить права доступа всех проектов
     *
     * @param projectId id проекта
     * @param user      пользователь
     * @return список доступов к проекту
     * @throws NotFoundPermissionException нету доступа для операции
     */
    List<ProjectPermission> getAllProjectPermissionOfProject(long projectId, User user);

}
