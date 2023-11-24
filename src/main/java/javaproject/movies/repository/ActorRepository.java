package javaproject.movies.repository;

import javaproject.movies.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Override
    List<Actor> findAll();
}
