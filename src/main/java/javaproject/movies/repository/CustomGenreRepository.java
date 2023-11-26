package javaproject.movies.repository;

import javaproject.movies.domain.Genre;

public interface CustomGenreRepository {
    void deleteGenre(Integer genreId);

    void updateGenre(Integer genreId, Genre genre);
}
