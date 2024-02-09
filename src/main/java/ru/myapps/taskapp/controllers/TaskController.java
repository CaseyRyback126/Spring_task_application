package ru.myapps.taskapp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.myapps.taskapp.Status;
import ru.myapps.taskapp.TaskService;
import ru.myapps.taskapp.models.Performer;
import ru.myapps.taskapp.models.Task;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Status status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}/status/{status}")
    public Task updateTaskStatus(@PathVariable Long id, @PathVariable Status status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/performers")
    public void assignPerformersToTask(@PathVariable Long taskId, @RequestBody Set<Performer> performers) {
        taskService.assignPerformersToTask(taskId, performers);
    }

    @DeleteMapping("/{taskId}/performers")
    public void removePerformersFromTask(@PathVariable Long taskId, @RequestBody Set<Performer> performers) {
        taskService.removePerformersFromTask(taskId, performers);
    }
}
