package javaproject.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javaproject.movies.domain.Movies;
import javaproject.movies.domain.Review;
import javaproject.movies.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImp implements CustomUserRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final MoviesRepository moviesRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            List<Movies> existingMovies = moviesRepository.findByUser(existingUser.get());
            for (Movies m : existingMovies) {
                List<Review> existingReviews = reviewRepository.findByMovie(m);
                for (Review r : existingReviews) {
                    entityManager.remove(r); // Delete reviews
                }
                entityManager.remove(m); // Delete movie after deleting the associated reviews
            }
            entityManager.flush(); // Flush the changes to the database

            List<Review> existingReviews = reviewRepository.findByUser(existingUser.get());
            for (Review r : existingReviews) {
                entityManager.remove(r); // Delete reviews that are associated with the user
            }

            entityManager.remove(existingUser.get()); // Finally, delete the user
        } else {
            throw new NoSuchElementException(String.format("No user found for id %s", userId));
        }
    }

    @Override
    @Transactional
    public void updateUser(Integer userId, User user) {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            existingUser.get().setUsername(user.getUsername());
            existingUser.get().setPassword(user.getPassword());
            existingUser.get().setEmail(user.getEmail());
            existingUser.get().setRole(user.getRole());
            entityManager.merge(existingUser.get());
        } else {
            throw new NoSuchElementException(String.format("No user found for id %s", userId));
        }
    }
}
