package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.*;
import co.edu.unal.se1back.Repository.*;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/api")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //tutor
    @GetMapping("/tutor")
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @GetMapping("/tutorStudents/{tutor_id}")
    public List<Student> getStudentInfo(@PathVariable (value = "tutor_id") Long tutorId){

        String sqlA = "SELECT * FROM student WHERE tutor_id=?";
        List<Student> students = jdbcTemplate.query(sqlA,new Object[]{tutorId}, (rs, rowNum) ->
                new Student(
                        rs.getLong("id"),
                        rs.getLong("document_number"),
                        rs.getString("document_type"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("rol"),
                        rs.getString("username"),
                        rs.getString("career"),
                        rs.getString("pa"),
                        rs.getString("papa"),
                        rs.getString("pappi"),
                        rs.getString("progress"),
                        null));
        return students;
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