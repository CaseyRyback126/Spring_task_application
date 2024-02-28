package ru.myapps.taskapp.models;

import java.time.LocalDateTime;

import static ru.myapps.taskapp.models.Status.NOT_STARTED;

public class MyTaskFactory implements TaskFactory{
    @Override
    public ITask createTask(Long id) {
        Task.TaskBuilder builder = new Task.TaskBuilder();
        builder.status(NOT_STARTED).createdAt(LocalDateTime.now()).id(id);
        return builder.build();
    }
}
