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
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

}