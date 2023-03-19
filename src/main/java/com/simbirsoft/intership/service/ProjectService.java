package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingProjectDto;
import com.simbirsoft.intership.dto.request.InvitingUserDto;
import com.simbirsoft.intership.dto.request.UpdatingProjectDto;
import com.simbirsoft.intership.dto.response.ProjectDto;
import com.simbirsoft.intership.dto.response.UserWithPermissionDto;
import com.simbirsoft.intership.exception.OwnerDeletingException;
import com.simbirsoft.intership.exception.ProjectClosedException;
import com.simbirsoft.intership.exception.TaskNotDoneException;
import com.simbirsoft.intership.model.Project;
import com.simbirsoft.intership.model.User;

/**
 * Сервис управления проекта
 */
public interface ProjectService {
    /**
     * Создать проект
     *
     * @param project шаблон для создания проекта
     * @param user    пользователь добавляющий проект
     * @return возвращает проект с ограниченными данными
     * @throws com.simbirsoft.intership.exception.AlreadyHasPermissionException связь между пользоватем и проектом уже существует
     */
    ProjectDto createProject(CreatingProjectDto project, User user);

    /**
     * Найти проект владельца
     *
     * @param projectId id проекта
     * @param user      владелец
     * @return возвращает проект
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException если не был найден проект
     */
    Project findProjectForOwner(long projectId, User user);

//    ProjectDto getProject(long projectId,User user);

    /**
     * Редактировать проект
     *
     * @param projectId          id проекта
     * @param updatingProjectDto данные для изменения
     * @param user               владелец
     * @return возвращает проект с ограниченными данными
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException не был найден проект
     */
    ProjectDto updateProject(long projectId, UpdatingProjectDto updatingProjectDto, User user);

    /**
     * Пригласить пользователя в проект
     *
     * @param projectId       проекта
     * @param invitingUserDto данные приглашаемого пользователя
     * @param user            владелец
     * @return возвращает пользователя с его правами
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException      не был найден проект
     * @throws com.simbirsoft.intership.exception.AlreadyHasPermissionException если пользователь уже добавлен
     * @throws com.simbirsoft.intership.exception.NotFoundUserException         не был найден участник
     */
    UserWithPermissionDto inviteUser(long projectId, InvitingUserDto invitingUserDto, User user);

    /**
     * Удалить пользователя из проекта
     *
     * @param projectId id пользователя
     * @param memberId  id удаляемого пользователя
     * @param user      владелец
     * @throws OwnerDeletingException                                           при потыке удалить владельца
     * @throws com.simbirsoft.intership.exception.AlreadyHasPermissionException если пользователь уже добавлен
     * @throws com.simbirsoft.intership.exception.NotFoundUserException         не был найден участник
     */
    void deleteUser(long projectId, long memberId, User user);

    /**
     * Завершить проект
     *
     * @param projectId id проекта
     * @param user      id владельца
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException    не был найден проект
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException если не были найдены права доступа
     * @throws TaskNotDoneException                                           если не все задачи выполнены
     * @throws ProjectClosedException                                         проект уже закрыт
     */
    void closeProject(long projectId, User user);
}



