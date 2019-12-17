package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.*;
import co.edu.unal.se1back.Repository.*;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @GetMapping("/studentInfoByUsername/{username}")
    public List<Student> getStudentInfo(@PathVariable (value = "username") String username)throws SQLException {

        String sqlA = "SELECT * FROM student WHERE username=?";
        List<Student> studentsInfo= jdbcTemplate.query(sqlA,new Object[]{username}, (rs, rowNum) ->
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

        return studentsInfo;

    }

    @GetMapping("/tutorIdByStudentUsername/{username}")
    public Long getTutorId(@PathVariable(value = "username") String username){
        return studentRepository.getTutorId(username);
    }

    @GetMapping("/studentTutorById/{id}")
    public List<Tutor> getTutorInfo(@PathVariable (value = "id") Long tutorId)throws SQLException{

        String sqlA = "SELECT * FROM tutor WHERE id=?";

        List<Tutor> tutor = jdbcTemplate.query(sqlA,new Object[]{tutorId} , (rs, rowNum) ->
                new Tutor(
                        rs.getLong("id"),
                        rs.getLong("document_number"),
                        rs.getString("document_type"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("rol"),
                        rs.getString("username"),
                        rs.getString("department"),
                        rs.getString("faculty"),
                        rs.getString("office"),
                        rs.getString("office_hours"),
                        null));

        return tutor;
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