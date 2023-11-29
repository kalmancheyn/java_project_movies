package javaproject.movies.repository;

import javaproject.movies.domain.Actor;
import javaproject.movies.domain.Genre;
import javaproject.movies.domain.Movies;
import javaproject.movies.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    @Override
    Page<Movies> findAll(Pageable pageable);

    @Query("select m from Movies m where m.movieId = ?1")
    Optional<Movies> findByMovieId(Integer movie_id);

    @Query("select m from Movies m where m.title = ?1")
    Optional<Movies> findByTitle(String title);

    @Query("select m from Movies m where m.actor = ?1")
    List<Movies> findByActor(Actor actor);

    @Query("select m from Movies m where m.genre = ?1")
    List<Movies> findByGenre(Genre genre);

    @Query("select m from Movies m where m.user = ?1")
    List<Movies> findByUser(User user);
}
