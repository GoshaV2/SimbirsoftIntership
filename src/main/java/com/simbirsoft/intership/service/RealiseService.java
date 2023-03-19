package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.CreatingRealiseDto;
import com.simbirsoft.intership.dto.response.RealiseDto;
import com.simbirsoft.intership.exception.NotFoundRealiseException;
import com.simbirsoft.intership.exception.ProjectClosedException;
import com.simbirsoft.intership.exception.RealiseHasTaskException;
import com.simbirsoft.intership.model.Realise;
import com.simbirsoft.intership.model.User;

import java.util.List;

/**
 * Сервис управляющий релизами проекта
 */
public interface RealiseService {

    /**
     * @param realise данные для добавления релиза
     * @param user    владелец проекта
     * @return ограниченные данные релиза
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException  не был найден проект
     * @throws com.simbirsoft.intership.exception.UncorrectedDatesException некоректные даты
     * @throws ProjectClosedException                                       проект закрыт
     */
    RealiseDto createRealise(CreatingRealiseDto realise, User user);

    /**
     * @param realiseId id релиза
     * @param projectId id проекта
     * @return релиз
     * @throws com.simbirsoft.intership.exception.NotFoundRealiseException не был найден релиз
     */
    Realise getRealise(long realiseId, long projectId);

    /**
     * @param realiseId id релиза
     * @param projectId id проекта
     * @param user      владелец проекта
     * @throws com.simbirsoft.intership.exception.NotFoundProjectException не был найден проект
     * @throws ProjectClosedException                                      проект закрыт
     * @throws RealiseHasTaskException                                     релиз содержит задачи
     * @throws NotFoundRealiseException                                    не найден релиз
     */
    void deleteRealise(long realiseId, long projectId, User user);

    /**
     * Получение релизов проекта
     *
     * @param projectId id проекта
     * @param user      пользователь
     * @return список релизов с ограниченными данными
     * @throws com.simbirsoft.intership.exception.NotFoundPermissionException нет доступа к проекту
     */
    List<RealiseDto> getRealisesOfProject(long projectId, User user);
}

