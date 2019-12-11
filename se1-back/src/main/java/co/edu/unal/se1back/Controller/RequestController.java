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
    UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //request
    @GetMapping("/request")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/requestInfoById/{id}")
    public List<Request> getrequestInfoById(@PathVariable (value = "id") Long id)throws SQLException, ResourceNotFoundException {

        String sqlA = "SELECT * FROM request WHERE receiver=?";
        List<Request> requestsInfo= jdbcTemplate.query(sqlA,new Object[]{id}, (rs, rowNum) ->
                new Request(
                        rs.getLong("id"),
                        userRepository.findById(rs.getLong("transmitter")).get(),
                        userRepository.findById(rs.getLong("receiver")).get(),
                        rs.getDate("request_date"),
                        rs.getString("message")));

        return requestsInfo;

    }
/*
    @GetMapping("/tutorIdByRequestUsername/{username}")
    public Long getTutorId(@PathVariable(value = "username") String username){
        return requestRepository.getTutorId(username);
    }

    @GetMapping("/requestTutorById/{id}")
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

    @PostMapping("/request")
    public Request createRequest(@Valid @RequestBody Request request) {
        return requestRepository.save(request);
    }

    @GetMapping("/request/{id_request}")
    public Request getRequestById(@PathVariable(value = "id_request") Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id_request", requestId));
    }

    @PutMapping("/request/{id_request}")
    public Request updateRequest(@PathVariable(value = "id_request") Long requestId,
                                 @Valid @RequestBody Request requestDetails) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id_request", requestId));



        request.setPapa(requestDetails.getPapa());
        request.setPa(requestDetails.getPa());
        request.setPappi(requestDetails.getPappi());
        request.setCareer(requestDetails.getCareer());
        request.setProgress(requestDetails.getProgress());
        request.setTutor(requestDetails.getTutor());

        Request updatedRequest = requestRepository.save(request);




        return updatedRequest;
    }

    @DeleteMapping("/request/{id_request}")
    public ResponseEntity<?> deleteRequest(@PathVariable(value = "id_request") Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id_request", requestId));

        requestRepository.delete(request);
        return ResponseEntity.ok().build();
    }
*/
}