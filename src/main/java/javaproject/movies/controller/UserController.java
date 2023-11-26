package javaproject.movies.controller;

import javaproject.movies.domain.Review;
import javaproject.movies.domain.User;
import javaproject.movies.repository.CustomUserRepository;
import javaproject.movies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private  final CustomUserRepository customUserRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        else {
            throw new NoSuchElementException(String.format("No user found for id %s", userId));
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String>  deleteUser(@PathVariable("userId") Integer userId) {
        customUserRepository.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<String>  updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        customUserRepository.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully!");
    }
}
