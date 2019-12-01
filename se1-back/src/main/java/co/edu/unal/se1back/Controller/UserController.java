package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.model.*;
import co.edu.unal.se1back.repository.*;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by javergarav on 20/11/19.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;


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


    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/student/{id_student}")
    public Student getStudentById(@PathVariable(value = "id_student") Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id_student", studentId));
    }

    @PutMapping("/student/{id_student}")
    public Student updateStudent(@PathVariable(value = "id_student") Long studentId,
                                 @Valid @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id_student", studentId));



        student.setPapa(studentDetails.getPapa());
        student.setPa(studentDetails.getPa());
        student.setPappi(studentDetails.getPappi());
        student.setCarrer(studentDetails.getCarrer());
        student.setProgress(studentDetails.getProgress());

        Student updatedStudent = studentRepository.save(student);




        return updatedStudent;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id_student") Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id_student", studentId));

        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }
}