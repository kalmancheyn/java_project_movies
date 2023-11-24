package javaproject.movies.repository;

import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Override
    List<Review> findAll();

    Optional<Review> findByMovie(Movies movie);


}
