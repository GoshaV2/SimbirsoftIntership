package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.dto.response.TaskDto;
import com.simbirsoft.intership.exception.ProjectClosedException;
import com.simbirsoft.intership.model.User;

/**
 * Сервис управления задачами
 */
public interface TaskService {
    /**
     * Создать задачу
     *
     * @param creatingTaskDto
     * @param user
     * @return задачу с ограниченными данными
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException не найдено прав на создание задачи
     *                                                                        или участник не состоит в проекте
     * @throws ProjectClosedException                                         проект закрыт
     * @throws com.simbirsoft.intership.exception.NotFoundRealiseException    не найден релиз
     */
    TaskDto createTask(CreatingTaskDto creatingTaskDto, User user);

    /**
     * Обновить задачу
     *
     * @param taskId
     * @param updatingTaskDto
     * @param user
     * @return задачу с ограниченными данными
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException не найдено прав на редактирование задачи
     *                                                                        или участник не состоит в проекте
     * @throws ProjectClosedException                                         проект закрыт
     * @throws com.simbirsoft.intership.exception.NotFoundRealiseException    не найден релиз
     * @throws com.simbirsoft.intership.exception.NotFoundTaskException       не была найдена задача
     */
    TaskDto updateTask(long taskId, UpdatingTaskDto updatingTaskDto, User user);

    /**
     * Сменить статус задачи
     *
     * @param taskId
     * @param changeTaskStatusDto
     * @param user
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException не найдено прав на смену статуса задачи
     *                                                                        или участник не состоит в проекте
     * @throws ProjectClosedException                                         проект закрыт
     * @throws com.simbirsoft.intership.exception.NotFoundTaskException       не найдена задача
     */
    void changeTaskStatus(long taskId, ChangeTaskStatusDto changeTaskStatusDto, User user);

    /**
     * Удалить задачу
     *
     * @param taskId
     * @param projectId
     * @param user
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException не найдено прав на смену статуса задачи
     *                                                                        или участник не состоит в проекте
     * @throws ProjectClosedException                                         проект закрыт
     * @throws com.simbirsoft.intership.exception.NotFoundTaskException       не найдена задача
     */
    void deleteTask(long taskId, long projectId, User user);
}
