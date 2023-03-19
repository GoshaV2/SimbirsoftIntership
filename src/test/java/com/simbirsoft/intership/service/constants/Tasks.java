package com.simbirsoft.intership.service.constants;

import com.simbirsoft.intership.model.Task;
import com.simbirsoft.intership.model.enumaration.TaskStatus;

public enum Tasks {
    TaskInProgress(Task.builder().id(1L).taskStatus(TaskStatus.IN_PROGRESS).title("Первого проекта")
            .owner(Users.User1.getUser()).performer(Users.User2.getUser())
            .project(Projects.ProjectOpen.getProject()).realise(Realises.RealiseWithTask.getRealise()).build()),
    TaskDone(Task.builder().id(2L).taskStatus(TaskStatus.IN_PROGRESS).title("Первого проекта сделан")
            .owner(Users.User1.getUser()).performer(Users.User1.getUser())
            .project(Projects.ProjectOpen.getProject()).realise(Realises.RealiseWithTask.getRealise()).build());
    private Task task;

    Tasks(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
