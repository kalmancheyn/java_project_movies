package javaproject.movies.repository;

import javaproject.movies.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Override
    List<Review> findAll();
}
