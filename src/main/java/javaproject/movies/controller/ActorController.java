package javaproject.movies.controller;

import javaproject.movies.domain.Actor;
import javaproject.movies.repository.ActorRepository;
import javaproject.movies.repository.CustomActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorController {
    private final ActorRepository actorRepository;
    private final CustomActorRepository customActorRepository;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @GetMapping(value = "/{actorId}")
    public Actor getActorById(@PathVariable("actorId") Integer actorId) {
        Optional<Actor> existingActor = actorRepository.findByActorId(actorId);
        if (existingActor.isPresent()) {
            return existingActor.get();
        }
        else {
            throw new NoSuchElementException(String.format("No actor found for id %s", actorId));
        }
    }

    @PostMapping
    public ResponseEntity<String> createActor(@RequestBody Actor actor){
        actorRepository.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Actor created successfully!");
    }

    @DeleteMapping(value = "/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable("actorId") Integer actorId) {
        customActorRepository.deleteActor(actorId);
        return ResponseEntity.status(HttpStatus.OK).body("Actor deleted successfully!");
    }
}
