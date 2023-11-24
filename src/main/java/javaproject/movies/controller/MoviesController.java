package javaproject.movies.controller;

import jakarta.transaction.Transactional;
import javaproject.movies.domain.Movies;
import javaproject.movies.repository.CustomMoviesRepository;
import javaproject.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return moviesRepository.findByMovieId(movie_id).get();
    }

    @PostMapping
    public Movies createMovie(@RequestBody Movies movie){
        Movies movies = customMoviesRepository.createMovie(movie);
        return movies;
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
