package ru.myapps.taskapp.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import ru.myapps.taskapp.Status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
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

    @OneToMany
    @JoinColumn(name = "performer_id")
    private Performer performer;

    @ManyToMany
    @JoinTable(
            name = "task_performer",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id")
    )
    private Set<Performer> performers = new HashSet<>();

    public Long getId() {
        return id;
    }

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

    public Set<Performer> getPerformers() {
        return performers;
    }
}
