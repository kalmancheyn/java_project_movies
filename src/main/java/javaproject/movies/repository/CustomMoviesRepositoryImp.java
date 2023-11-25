package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CustomMoviesRepositoryImp implements CustomMoviesRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final MoviesRepository moviesRepository;
    private final UserRepository userRepository;
    private final ActorRepository actorRepository;
    private  final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Movies createMovie(Movies movie) {
        Movies movies = new Movies();

        //check if user exists
        Optional<User> existingUser = userRepository.findById(movie.getUser().getUserId());
        if (existingUser.isEmpty()) {
            throw new NoSuchElementException(String.format("No user found for id %s", movie.getUser().getUsername()));
        }

        //check if actor exists
        Optional<Actor> existingActor = actorRepository.findById(movie.getActor().getActorId());
        if (existingActor.isEmpty()) {
            throw new NoSuchElementException(String.format("No actor found for id %s", movie.getActor().getName()));
        }

        //check if genre exists
        Optional<Genre> existingGenre = genreRepository.findById(movie.getGenre().getGenreId());
        if (existingGenre.isEmpty()) {
            throw new NoSuchElementException(String.format("No genre found for id %s", movie.getGenre().getName()));
        }

        //check if movie already exists
        Optional<Movies> existingMovie = moviesRepository.findByTitle(movie.getTitle());
        if (existingMovie.isEmpty()) {
            movies.setTitle(movie.getTitle());
            movies.setReleaseDate(movie.getReleaseDate());
            movies.setGenre(existingGenre.get());
            movies.setDirector(movie.getDirector());
            movies.setActor(movie.getActor());
            movies.setAverageRating(movie.getAverageRating());
            movies.setDuration(movie.getDuration());
            movies.setDescription(movie.getDescription());
            movies.setUser(existingUser.get());
            movies.setActor(existingActor.get());
            entityManager.persist(movies);
        }
        return movies;
    }

    @Override
    @Transactional
    public void deleteMovie(Integer movieId) {
        Optional<Movies> existingMovie = moviesRepository.findById(Math.toIntExact(movieId));
        if (existingMovie.isPresent()) {
            List<Review> existingReview = reviewRepository.findByMovie(existingMovie.get());
            for (Review r : existingReview) {
                entityManager.remove(r);
            }
            entityManager.remove(existingMovie.get());
        }
        else {
            throw new NoSuchElementException(String.format("No movie found for id %s", existingMovie.get().getMovieId()));
        }
    }

    @Override
    @Transactional
    public void updateMovie(Integer movieId, Movies movie) {

        Optional<Movies> existingMovie = moviesRepository.findById(Math.toIntExact(movieId));
        if (existingMovie.isPresent()) {
            existingMovie.get().setTitle(movie.getTitle());
            existingMovie.get().setReleaseDate(movie.getReleaseDate());
            existingMovie.get().setGenre(movie.getGenre());
            existingMovie.get().setDirector(movie.getDirector());
            existingMovie.get().setActor(movie.getActor());
            existingMovie.get().setAverageRating(movie.getAverageRating());
            existingMovie.get().setDuration(movie.getDuration());
            existingMovie.get().setDescription(movie.getDescription());
            entityManager.merge(existingMovie.get());
        }
        else {
            throw new NoSuchElementException(String.format("No movie found for id %s", existingMovie.get().getMovieId()));
        }
    }
}
