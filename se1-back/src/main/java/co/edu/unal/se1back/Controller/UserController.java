package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.model.*;
import co.edu.unal.se1back.repository.*;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TutorRepository tutorRepository;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));



        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setDocument_type(userDetails.getDocument_type());
        user.setDocument_number(userDetails.getDocument_number());
        user.setRol(userDetails.getRol());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());


        User updatedUser = userRepository.save(user);




        return updatedUser;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}