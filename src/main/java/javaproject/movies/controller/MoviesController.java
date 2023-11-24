package javaproject.movies.controller;

import javaproject.movies.domain.Movies;
import javaproject.movies.repository.CustomMoviesRepository;
import javaproject.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MoviesController {
    private final MoviesRepository moviesRepository;
    private final CustomMoviesRepository customMoviesRepository;

    @GetMapping
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @GetMapping("/{movieId}")
    public Movies getMovieById(@PathVariable("movieId") Integer movie_id) {
        Optional<Movies> existingMovie = moviesRepository.findByMovieId(movie_id);
        if (existingMovie.isPresent()) {
            return existingMovie.get();
        }
        else {
            throw new NoSuchElementException(String.format("No movie found for id %s", movie_id));
        }
    }

    @PostMapping
    public Movies createMovie(@RequestBody Movies movie){
        return customMoviesRepository.createMovie(movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        customMoviesRepository.deleteMovie(movieId);
    }

    @PutMapping(value = "/{movieId}")
    public void updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movies movie) {
        customMoviesRepository.updateMovie(movieId, movie);
    }
}
