package ru.myapps.taskapp;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapps.taskapp.models.Performer;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
