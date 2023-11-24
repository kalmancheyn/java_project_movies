package javaproject.movies.controller;

import jakarta.transaction.Transactional;
import javaproject.movies.domain.Movies;
import javaproject.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MoviesController {
    private final MoviesRepository moviesRepository;

    @GetMapping
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @GetMapping("/{movie_id}")
    public Movies getMovieById(@PathVariable("movie_id") Integer movie_id) {
        return moviesRepository.findByMovieId(movie_id).get();
    }

    @PostMapping
    @Transactional
    public Movies createMovie(@RequestBody Movies movie) {
        return moviesRepository.save(movie);
    }

    @PutMapping("/{movie_id}")
    @Transactional
    public Movies updateMovie(@PathVariable("movie_id") Integer movie_id, @RequestBody Movies movie) {
        Movies movieToUpdate = moviesRepository.findByMovieId(movie_id).get();
        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setReleaseDate(movie.getReleaseDate());
        movieToUpdate.setDuration(movie.getDuration());
        movieToUpdate.setDescription(movie.getDescription());
        movieToUpdate.setAverageRating(movie.getAverageRating());
        return moviesRepository.save(movieToUpdate);
    }
}
