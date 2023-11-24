package javaproject.movies.repository;

import javaproject.movies.domain.Movies;

public interface CustomMoviesRepository {
    Movies createMovie(Movies movie);

    void deleteMovie(Long movie);

    void updateMovie(Long movieId, Movies movie);
}
