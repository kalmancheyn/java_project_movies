package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import javaproject.movies.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomReviewRepositoryImp implements CustomReviewRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final ReviewRepository reviewRepository;
    private final MoviesRepository moviesRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Review createReview(Review review) {
        Review newReview = new Review();

        //check if movie exists
        Optional<Movies> existingMovie = moviesRepository.findByMovieId(review.getMovie().getMovieId());
        if (existingMovie.isEmpty()) {
            throw new NoSuchElementException(String.format("No movie found for id %s", review.getMovie().getTitle()));
        }

        //check if user exists
        Optional<User> existingUser = userRepository.findById(review.getUser().getUserId());
        if (existingUser.isEmpty()) {
            throw new NoSuchElementException(String.format("No user found for id %s", review.getUser().getUsername()));
        }

        newReview.setMovie(review.getMovie());
        newReview.setUser(review.getUser());
        newReview.setRating(review.getRating());
        newReview.setComment(review.getComment());
        entityManager.persist(newReview);

        return newReview;
    }

    @Override
    @Transactional
    public void deleteReview(Integer reviewId) {
        Optional<Review> existingReview = reviewRepository.findByReviewId(reviewId);
        if (existingReview.isPresent()) {
            entityManager.remove(existingReview.get());
        } else {
            throw new NoSuchElementException(String.format("No review found for id %s", reviewId));
        }
    }

    @Override
    @Transactional
    public void updateReview(Integer reviewId, Review review) {
        Optional<Review> existingReview = reviewRepository.findByReviewId(reviewId);
        if (existingReview.isPresent()) {
            existingReview.get().setRating(review.getRating());
            existingReview.get().setComment(review.getComment());
            entityManager.persist(existingReview.get());
        } else {
            throw new NoSuchElementException(String.format("No review found for id %s", reviewId));
        }
    }
}
