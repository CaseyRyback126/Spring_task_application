package ru.myapps.taskapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myapps.taskapp.repository.TaskRepository;
import ru.myapps.taskapp.models.Performer;
import ru.myapps.taskapp.models.Status;
import ru.myapps.taskapp.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public static boolean isCompleted(Status status){return true;}

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional
    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Transactional
    public Task createTask(Task task) {
        if (task.findPerformers() != null) {
            taskRepository.findPerformers(task.findPerformers()).ifPresent(existingTask -> {
                throw new RuntimeException("The performers already has a task");
            });
        }
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Task task) {
        return taskRepository.findById(task.getTaskId()).map(existingTask -> {
            if (task.getPerformer() != null && task.getPerformer().getId() != null
                    && !task.getPerformer().getId().equals(existingTask.getPerformer().getId())) {
                taskRepository.findByPerformerId(task.getPerformer().getId()).ifPresent(t -> {
                    throw new RuntimeException("The performer already has a task");
                });
            }
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void assignPerformersToTask(Long taskId, Set<Performer> performers) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.findPerformers().addAll(performers);
            taskRepository.save(task);
        }
    }

    public void removePerformersFromTask(Long taskId, Set<Performer> performers) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.findPerformers().removeAll(performers);
            taskRepository.save(task);
        }
    }
}
