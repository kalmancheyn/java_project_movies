package javaproject.movies.controller;

import javaproject.movies.domain.Genre;
import javaproject.movies.repository.CustomGenreRepository;
import javaproject.movies.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreRepository genreRepository;
    private  final CustomGenreRepository customGenreRepository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping(value = "/{genreId}")
    public Genre getGenreById(@PathVariable("genreId") Integer genreId) {
        Optional<Genre> existingGenre = genreRepository.findByGenreId(genreId);
        if (existingGenre.isPresent()) {
            return existingGenre.get();
        }
        else {
            throw new NoSuchElementException(String.format("No genre found for id %s", genreId));
        }
    }

    @PostMapping
    public ResponseEntity<String> createGenre(@RequestBody Genre genre) {
        genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body("Genre created successfully!");
    }

    @DeleteMapping(value = "/{genreId}")
    public ResponseEntity<String> deleteGenre(@PathVariable("genreId") Integer genreId) {
        customGenreRepository.deleteGenre(genreId);
        return ResponseEntity.status(HttpStatus.OK).body("Genre deleted successfully!");
    }

    @PutMapping(value = "/{genreId}")
    public ResponseEntity<String> updateGenre(@PathVariable("genreId") Integer genreId, @RequestBody Genre genre) {
        customGenreRepository.updateGenre(genreId, genre);
        return ResponseEntity.status(HttpStatus.OK).body("Genre updated successfully!");
    }
}
