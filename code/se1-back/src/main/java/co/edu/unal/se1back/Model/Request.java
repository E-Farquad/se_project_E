package co.edu.unal.se1back.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
import co.edu.unal.se1back.Model.*;


@Entity(name = "request")
public class Request{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student")
    private Student student;

    @ManyToOne
    @JoinColumn(name="tutor")
    private Tutor tutor;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date request_date;

    @NotBlank
    private String message;

    @NotNull
    private Long transmitter;


    @NotNull
    private Long receiver;

    public Request(Long id, Student student, Tutor tutor, Date request_date, String message, Long transmitter, Long receiver) {
        this.id = id;
        this.student = student;
        this.tutor = tutor;
        this.request_date = request_date;
        this.message = message;
        this.transmitter = transmitter;
        this.receiver = receiver;
    }

    public Request() {
        this.id = null;
        this.student = null;
        this.tutor = null;
        this.request_date = null;
        this.message = null;
        this.transmitter = null;
        this.receiver = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(Long transmitter) {
        this.transmitter = transmitter;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }
}