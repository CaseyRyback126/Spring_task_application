package ru.myapps.taskapp.service;

import ru.myapps.taskapp.models.Performer;
import ru.myapps.taskapp.models.Status;
import ru.myapps.taskapp.models.Task;

import java.util.List;
import java.util.Set;

public interface TaskServiceFacade {
    boolean completeTask(Long id);

    Task addTask(Task task);

    List<Task> getAllTasks();

    List<Task> getTasksByStatus(Status status);

    Task updateTaskStatus(Long id, Status status);

    void deleteTask(Long id);

    void assignPerformersToTask(Long taskId, Set<Performer> performers);

    void removePerformersFromTask(Long taskId, Set<Performer> performers);
}
