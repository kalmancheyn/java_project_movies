package javaproject.movies.controller;

import javaproject.movies.domain.Movies;
import javaproject.movies.repository.CustomMoviesRepository;
import javaproject.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MoviesController {
    private final MoviesRepository moviesRepository;
    private final CustomMoviesRepository customMoviesRepository;

    @GetMapping
    public List<Movies> getMovies(@RequestParam(required = false, defaultValue = "100") Integer limit, @RequestParam(required = false, defaultValue = "asc") String sort) {
        if ( !sort.equalsIgnoreCase("desc") && !sort.equalsIgnoreCase("asc") ) {
            throw new IllegalArgumentException("Invalid sorting param!!!");
        }
        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "title") : Sort.by(Sort.Direction.DESC, "title");

        Page<Movies> movies = moviesRepository.findAll(PageRequest.of(0, limit, sortParam ));

        return movies.toList();
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

    @PostMapping("/upload-xlsx")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Validate if the file is an XLSX
        if (!Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Only XLSX files are allowed");
        }

        try (InputStream inputStream = file.getInputStream()) {
            // Create a workbook instance from the uploaded file
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Assuming the first sheet contains movie information
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows to read movie information
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Assuming the columns contain: Title, Release Date, Description, Duration, Director, Average Rating, Poster URL
                String title = row.getCell(0).getStringCellValue().trim();
                String releaseDateString = row.getCell(1).getStringCellValue().trim();
                LocalDate releaseDate = LocalDate.parse(releaseDateString);
                String description = row.getCell(2).getStringCellValue().trim();
                int duration = (int) row.getCell(3).getNumericCellValue();
                String director = row.getCell(4).getStringCellValue().trim();
                BigDecimal averageRating = BigDecimal.valueOf(row.getCell(5).getNumericCellValue());
                String posterUrl = row.getCell(6).getStringCellValue().trim();

                // Create a Movies object and add it to the list
                Movies movie = new Movies();
                movie.setTitle(title);
                movie.setReleaseDate(releaseDate);
                movie.setDescription(description);
                movie.setDuration(duration);
                movie.setDirector(director);
                movie.setAverageRating(averageRating);
                movie.setPosterUrl(posterUrl);

                customMoviesRepository.createMovie(movie);
            }

            return ResponseEntity.ok("Movies uploaded and saved to the database!");
        } catch (IOException | EncryptedDocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload and save movies: " + e.getMessage());
        }
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
