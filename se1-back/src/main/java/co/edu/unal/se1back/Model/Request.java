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
    @JoinColumn(name="transmitter")
    private User transmitter;

    @ManyToOne
    @JoinColumn(name="receiver")
    private User receiver;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date request_date;

    @NotBlank
    private String message;

    public Request(Long id, User transmitter, User receiver, Date request_date, String message) {
        this.id = id;
        this.transmitter = transmitter;
        this.receiver = receiver;
        this.request_date = request_date;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(User transmitter) {
        this.transmitter = transmitter;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
}