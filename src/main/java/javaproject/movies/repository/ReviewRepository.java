package javaproject.movies.repository;

import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Override
    List<Review> findAll();

    @Query("select r from Review r where r.movie = ?1")
    List<Review> findByMovie(Movies movie);
}
