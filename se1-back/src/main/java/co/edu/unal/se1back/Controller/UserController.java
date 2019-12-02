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

    //student
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
        student.setCareer(studentDetails.getCareer());
        student.setProgress(studentDetails.getProgress());
        student.setTutor(studentDetails.getTutor());

        Student updatedStudent = studentRepository.save(student);




        return updatedStudent;
    }

    @DeleteMapping("/student/{id_student}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id_student") Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id_student", studentId));

        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    //tutor
    @GetMapping("/tutor")
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @PostMapping("/tutor")
    public Tutor createTutor(@Valid @RequestBody Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @GetMapping("/tutor/{id_tutor}")
    public Tutor getTutorById(@PathVariable(value = "id_tutor") Long tutorId) {
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor", "id_tutor", tutorId));
    }

    @PutMapping("/tutor/{id_tutor}")
    public Tutor updateTutor(@PathVariable(value = "id_tutor") Long tutorId,
                                 @Valid @RequestBody Tutor tutorDetails) {

        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor", "id_tutor", tutorId));



        tutor.setOffice(tutorDetails.getOffice());
        tutor.setOffice_hours(tutorDetails.getOffice_hours());
        tutor.setFaculty(tutorDetails.getFaculty());
        tutor.setDepartment(tutorDetails.getDepartment());
        tutor.setStudents(tutorDetails.getStudents());


        Tutor updatedTutor = tutorRepository.save(tutor);

        return updatedTutor;
    }

    @DeleteMapping("/tutor/{id_tutor}")
    public ResponseEntity<?> deleteTutor(@PathVariable(value = "id_tutor") Long tutorId) {

        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor", "id_tutor", tutorId));

        tutorRepository.delete(tutor);
        return ResponseEntity.ok().build();
    }


}