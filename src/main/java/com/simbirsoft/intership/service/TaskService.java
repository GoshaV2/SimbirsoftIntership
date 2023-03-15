package com.simbirsoft.intership.service;

import com.simbirsoft.intership.dto.request.ChangeTaskStatusDto;
import com.simbirsoft.intership.dto.request.CreatingTaskDto;
import com.simbirsoft.intership.dto.request.UpdatingTaskDto;
import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.User;

public interface TaskService {
    Task createTask(CreatingTaskDto creatingTaskDto, User user);

    Task updateTask(long taskId,UpdatingTaskDto updatingTaskDto, User user);

    Task changeTaskStatus(long taskId, ChangeTaskStatusDto changeTaskStatusDto,User user);

    void deleteTask(long taskId,long projectId,User user);
}
