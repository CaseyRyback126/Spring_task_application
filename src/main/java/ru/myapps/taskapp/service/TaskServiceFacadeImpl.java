package ru.myapps.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.myapps.taskapp.models.*;
import ru.myapps.taskapp.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TaskServiceFacadeImpl implements TaskServiceFacade {
    private TaskRepository taskRepository;
    @Autowired
    private MyTaskFactory taskFactory;

    @Override
    public boolean completeTask(Long taskId) {
        boolean taskCompleted = false;

        ITask task = taskFactory.createTask(taskId);
        if (TaskService.isCompleted(Status.COMPLETED)) {
            System.out.println("Task with id: " + task.getTaskId() + "is completed");
            taskCompleted = true;
        } else if (TaskService.isCompleted(Status.IN_PROGRESS))
            System.out.println("Task with id: " + task.getTaskId() + "in progress");

        return taskCompleted;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid task Id:" + id));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void assignPerformersToTask(Long taskId, Set<Performer> performers) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.findPerformers().addAll(performers);
            taskRepository.save(task);}
    }

    @Override
    public void removePerformersFromTask(Long taskId, Set<Performer> performers) {
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                task.findPerformers().removeAll(performers);
                taskRepository.save(task);
            }
    }
}
