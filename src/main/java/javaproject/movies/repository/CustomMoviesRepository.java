package javaproject.movies.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import javaproject.movies.domain.Movies;

public interface CustomMoviesRepository {
    Movies createMovie(Movies movie);

    void deleteMovie(Integer movieId);

    void updateMovie(Integer movieId, Movies movie);
}
