package javaproject.movies.repository;

import javaproject.movies.domain.Actor;
import javaproject.movies.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    @Override
    List<Movies> findAll();

    @Query("select m from Movies m where m.movieId = ?1")
    Optional<Movies> findByMovieId(Integer movie_id);

    @Query("select m from Movies m where m.title = ?1")
    Optional<Movies> findByTitle(String title);

    @Query("select m from Movies m where m.actor = ?1")
    List<Movies> findByActor(Actor actor);


}
