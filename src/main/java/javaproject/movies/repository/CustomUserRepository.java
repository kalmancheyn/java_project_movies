package javaproject.movies.repository;

import javaproject.movies.domain.User;

public interface CustomUserRepository {
    void deleteUser(Integer userId);

    void updateUser(Integer userId,User user);
}
