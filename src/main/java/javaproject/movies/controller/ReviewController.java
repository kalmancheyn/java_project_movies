package javaproject.movies.controller;

import javaproject.movies.domain.Review;
import javaproject.movies.repository.CustomReviewRepository;
import javaproject.movies.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final CustomReviewRepository customReviewRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> existingReview = reviewRepository.findByReviewId(reviewId);
        if (existingReview.isPresent()) {
            return existingReview.get();
        }
        else {
            throw new NoSuchElementException(String.format("No review found for id %s", reviewId));
        }
    }
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review){
        customReviewRepository.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully!");
    }

    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<String>  deleteReview(@PathVariable("reviewId") Integer reviewId) {
        customReviewRepository.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully!");
    }

    @PutMapping(value = "/{reviewId}")
    public ResponseEntity<String>  updateReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Review review) {
        customReviewRepository.updateReview(reviewId, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully!");
    }
}
