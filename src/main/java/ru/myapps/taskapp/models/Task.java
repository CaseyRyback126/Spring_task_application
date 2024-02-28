package ru.myapps.taskapp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task implements ITask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;

    @ManyToMany
    @JoinTable(
            name = "task_performer",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id")
    )
    private Set<Performer> performers = new HashSet<>();

    @Override
    public Long getTaskId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Task() {
    }

    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Performer getPerformer() {
        return performer;
    }

    public Set<Performer> findPerformers() {
        return performers;
    }

    public static class TaskBuilder {
        private final Task task = new Task();

        public TaskBuilder status(Status status) {
            task.setStatus(status);
            return this;
        }

        public TaskBuilder id(Long taskId) {
            task.setTaskId(taskId);
            return this;
        }

        public TaskBuilder createdAt(LocalDateTime time) {
            task.setCreatedAt(time);
            return this;
        }

        public Task build() {
            return task;
        }
    }

    private void setTaskId(Long id) {
        this.id = id;
    }
}


