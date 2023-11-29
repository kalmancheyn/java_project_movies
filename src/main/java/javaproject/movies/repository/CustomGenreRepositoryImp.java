package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.Genre;
import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomGenreRepositoryImp implements CustomGenreRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final GenreRepository genreRepository;
    private final MoviesRepository moviesRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void deleteGenre(Integer genreId) {
        Optional<Genre> existingGenre = genreRepository.findByGenreId(genreId);
        if (existingGenre.isPresent()) {
            List<Movies> existingMovies = moviesRepository.findByGenre(existingGenre.get());
            for (Movies m : existingMovies) {
                List<Review> reviews = reviewRepository.findByMovie(m);
                for (Review r : reviews) {
                    entityManager.remove(r);
                }
                entityManager.remove(m);
            }
            entityManager.remove(existingGenre.get());
        } else {
            throw new NoSuchElementException(String.format("No genre found for id %s", genreId));
        }
    }

    @Override
    @Transactional
    public void updateGenre(Integer genreId, Genre genre) {
        Optional<Genre> existingGenre = genreRepository.findByGenreId(genreId);
        if (existingGenre.isPresent()) {
            existingGenre.get().setName(genre.getName());
            entityManager.persist(existingGenre.get());
        } else {
            throw new NoSuchElementException(String.format("No genre found for id %s", genreId));
        }
    }
}
