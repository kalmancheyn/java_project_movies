package javaproject.movies.repository;

import javaproject.movies.domain.Review;

public interface CustomReviewRepository {
    Review createReview(Review review);

    void deleteReview(Integer reviewId);

    void updateReview(Integer reviewId, Review review);
}
