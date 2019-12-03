package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.Student;
import co.edu.unal.se1back.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TutorController {

    @Autowired
    StudentRepository studentRepository;

    /*
        Get all info about the students that the tutor has
        Exceptions
     */


    @GetMapping("/student/{tutor_id}")
    public List<Student> studentList (@PathVariable(value = "tutor_id") Long tutorId ){
        return studentRepository.getStudents(tutorId);
    }



}
