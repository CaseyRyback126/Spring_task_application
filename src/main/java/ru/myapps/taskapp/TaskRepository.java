package ru.myapps.taskapp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapps.taskapp.models.Performer;
import ru.myapps.taskapp.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

    Optional<Object> findPerformers(Set<Performer> performers);

    Optional<Object> findByPerformerId(Long id);
}
