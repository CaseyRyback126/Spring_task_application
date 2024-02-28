package ru.myapps.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myapps.taskapp.repository.PerformerRepository;
import ru.myapps.taskapp.custannotations.TrackPerformerAction;
import ru.myapps.taskapp.models.Performer;

import java.util.List;

@Service
public class PerformerService {
    @Autowired
    private final PerformerRepository performerRepository;

    public PerformerService(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    public List<Performer> getAllPerformers() {
        return performerRepository.findAll();
    }

    @TrackPerformerAction
    public Performer getPerformerById(Long id) {
        return performerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performer not found"));
    }

    @TrackPerformerAction
    public Performer createPerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    @TrackPerformerAction
    public Performer updatePerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    public void deletePerformer(Long id) {
        performerRepository.deleteById(id);
    }
}
