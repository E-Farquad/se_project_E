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
    TutorRepository tutorRepository;

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