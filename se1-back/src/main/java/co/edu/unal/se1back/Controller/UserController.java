package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.*;
import co.edu.unal.se1back.Repository.*;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.sql.*;
import javax.validation.Valid;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    //simplemente verificar si está en la bse de datos, sea estudiante o tutor
    @PostMapping("/VerifyUser")
    public Boolean loginVerification(@Valid @RequestBody Verifiable user)throws SQLException{
        String sqlA = "SELECT * FROM student WHERE username like ? AND password like ?";

        List<Student> respuesta = jdbcTemplate.query(sqlA,new Object[]{"%" + user.getUsername() + "%", user.getPassword()} , (rs, rowNum) ->
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

        if(respuesta.size() >0)return true;

        sqlA = "SELECT * FROM tutor WHERE username like ? AND password like ?";

        List<Tutor> respuesta2 = jdbcTemplate.query(sqlA,new Object[]{"%" + user.getUsername() + "%", user.getPassword()} , (rs, rowNum) ->
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
        if(respuesta2.size() >0)return true;
        else return false;

    }



    //Verificar especificamente si es un estudiante
    @PostMapping("/VerifyStudent")
    public Boolean studentLoginVerification(@Valid @RequestBody Verifiable user)throws SQLException {
        String sqlA = "SELECT * FROM student WHERE username like ? AND password like ?";

        List<Student> respuesta = jdbcTemplate.query(sqlA, new Object[]{"%" + user.getUsername() + "%", user.getPassword()}, (rs, rowNum) ->
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

        if (respuesta.size() > 0) return true;
        else return false;
    }

    //verificar especificamente si es un tutor
    @PostMapping("/VerifyTutor")
    public Boolean tutorLoginVerification(@Valid @RequestBody Verifiable user)throws SQLException {
        String sqlA = "SELECT * FROM tutor WHERE username like ? AND password like ?";

        List<Tutor> respuesta2 = jdbcTemplate.query(sqlA, new Object[]{"%" + user.getUsername() + "%", user.getPassword()}, (rs, rowNum) ->
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
        if (respuesta2.size() > 0) return true;
        else return false;
    }





    /*
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

*/



}