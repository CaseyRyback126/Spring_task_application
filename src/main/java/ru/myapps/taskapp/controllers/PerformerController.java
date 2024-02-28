package ru.myapps.taskapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.myapps.taskapp.service.PerformerService;
import ru.myapps.taskapp.models.Performer;

import java.util.List;

@RestController
@RequestMapping("api/performers")
public class PerformerController {
    private final PerformerService performerService;

    @Autowired
    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @GetMapping
    public List<Performer> getAllPerformers(){
        return performerService.getAllPerformers();
    }

    @GetMapping("{/id}")
    public Performer getPerformerById(@PathVariable Long id){
        return performerService.getPerformerById(id);
    }

    @PostMapping
    public Performer createPerformer(@RequestBody Performer performer){
        return performerService.createPerformer(performer);
    }

    @PutMapping
    public Performer updatePerformer(@RequestBody Performer performer){
        return performerService.updatePerformer(performer);
    }

    @DeleteMapping("/{id}")
    public void deletePerformer(@PathVariable Long id){
        performerService.deletePerformer(id);
    }
}
