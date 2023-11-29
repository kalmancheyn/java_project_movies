package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.Actor;
import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomActorRepositoryImp  implements CustomActorRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final ActorRepository actorRepository;
    private final MoviesRepository moviesRepository;
    private final ReviewRepository reviewRepository;

//    @Override
//    @Transactional
//    public Actor createActor(Actor newActor) {
//        Actor actor = new Actor();
//        Optional<Actor> existingActor = actorRepository.findByName(newActor.getName());
//        if (existingActor.isEmpty()) {
//            actor.setName(newActor.getName());
//            actor.setBiography(newActor.getBiography());
//            actor.setNationality(newActor.getNationality());
//            actor.setBirthdate(newActor.getBirthdate());
//            entityManager.persist(actor);
//        }
//
//        return actor;
//    }

    @Override
    @Transactional
    public void deleteActor(Integer actorId) {
        Optional<Actor> existingActor = actorRepository.findByActorId(actorId);
        if (existingActor.isPresent()) {
            List<Movies> existingMovies = moviesRepository.findByActor(existingActor.get());
            for (Movies m : existingMovies) {
                List<Review> reviews = reviewRepository.findByMovie(m);
                for (Review r : reviews) {
                    entityManager.remove(r);
                }
                entityManager.remove(m);
            }
            entityManager.remove(existingActor.get());
        } else {
            throw new NoSuchElementException(String.format("No actor found for id %s", actorId));
        }
    }

    @Override
    @Transactional
    public void updateActor(Integer actorId, Actor actor) {
        Optional<Actor> existingActor = actorRepository.findByActorId(actorId);
        if (existingActor.isPresent()) {
            existingActor.get().setName(actor.getName());
            existingActor.get().setBiography(actor.getBiography());
            existingActor.get().setNationality(actor.getNationality());
            existingActor.get().setBirthdate(actor.getBirthdate());
            entityManager.persist(existingActor.get());
        } else {
            throw new NoSuchElementException(String.format("No actor found for id %s", actorId));
        }
    }
}
