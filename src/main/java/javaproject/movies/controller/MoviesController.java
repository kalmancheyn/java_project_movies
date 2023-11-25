package javaproject.movies.controller;

import javaproject.movies.domain.Movies;
import javaproject.movies.repository.CustomMoviesRepository;
import javaproject.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createMovie(@RequestBody Movies movie){
        customMoviesRepository.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie created successfully!");
    }

    @DeleteMapping(value = "/{movieId}")
    public ResponseEntity<String>  deleteMovie(@PathVariable("movieId") Integer movieId) {
        customMoviesRepository.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully!");
    }

    @PutMapping(value = "/{movieId}")
    public ResponseEntity<String>  updateMovie(@PathVariable("movieId") Integer movieId, @RequestBody Movies movie) {
        customMoviesRepository.updateMovie(movieId, movie);
        return ResponseEntity.status(HttpStatus.OK).body("Movie updated successfully!");
    }
}
