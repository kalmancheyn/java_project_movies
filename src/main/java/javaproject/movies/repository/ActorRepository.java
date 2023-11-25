package javaproject.movies.repository;

import javaproject.movies.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Override
    List<Actor> findAll();

    @Query("select a from Actor a where a.actorId = ?1")
    Optional<Actor> findByActorId(Integer actorId);

    @Query("select a from Actor a where a.name = ?1")
    Optional<Actor> findByName(String name);


}
