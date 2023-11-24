package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CustomMoviesRepositoryImp implements CustomMoviesRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final MoviesRepository moviesRepository;
//
//
//    @Override
//    @Transactional
//    public ResponseEntity<Movies> createMovie(Movies movie) {
//
//    }
//
//    @Override
//    public void deleteMovie(Movies movie) {
//
//    }
}
