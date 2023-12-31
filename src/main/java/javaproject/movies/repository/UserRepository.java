package javaproject.movies.repository;

import javaproject.movies.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();

    @Query("select u from User u where u.userId = ?1")
    Optional<User> findByUserId(Integer userId);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
