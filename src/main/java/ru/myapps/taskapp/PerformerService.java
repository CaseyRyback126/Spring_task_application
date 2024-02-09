package ru.myapps.taskapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Performer getPerformerById(Long id) {
        return performerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performer not found"));
    }

    public Performer createPerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    public Performer updatePerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    public void deletePerformer(Long id) {
        performerRepository.deleteById(id);
    }
}
