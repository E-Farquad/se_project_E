package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.Tutor;
import co.edu.unal.se1back.Repository.StudentRepository;
import co.edu.unal.se1back.Repository.TutorRepository;
import co.edu.unal.se1back.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    TutorRepository tutorRepository;

    /*
        Get the student's tutor, and return a tutor object with the info
    */

    @GetMapping("/tutor/{id}")
    public Tutor studentTutor(@PathVariable(value = "id") Long tutorId) {
        return tutorRepository.getTutorById(tutorId);
    }

}
