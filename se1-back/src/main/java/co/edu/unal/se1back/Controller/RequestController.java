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
import java.util.*;
import java.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/requestByTutorId/{id}")
    public List<Request> getRequestByTutorId(@PathVariable (value = "id") Long id)throws SQLException, ResourceNotFoundException {

        String sqlA = "SELECT * FROM request WHERE tutor=?";
        List<Request> requestsInfo= jdbcTemplate.query(sqlA,new Object[]{id}, (rs, rowNum) ->
                new Request(
                        rs.getLong("id"),
                        studentRepository.findById(rs.getLong("student")).get(),
                        tutorRepository.findById(rs.getLong("tutor")).get(),
                        rs.getDate("request_date"),
                        rs.getString("message")));

        return requestsInfo;

    }

    @GetMapping("/requestByStudentId/{id}")
    public List<Request> getRequestByStudentId(@PathVariable (value = "id") Long id)throws SQLException, ResourceNotFoundException {

        String sqlA = "SELECT * FROM request WHERE student=?";
        List<Request> requestsInfo= jdbcTemplate.query(sqlA,new Object[]{id}, (rs, rowNum) ->
                new Request(
                        rs.getLong("id"),
                        studentRepository.findById(rs.getLong("student")).get(),
                        tutorRepository.findById(rs.getLong("tutor")).get(),
                        rs.getDate("request_date"),
                        rs.getString("message")));

        return requestsInfo;

    }

    @PostMapping("/request")
    public Request createRequest(@Valid @RequestBody Request request) {
        return requestRepository.save(request);
    }

}