package co.edu.unal.unacompaamiento.model;

import java.util.Date;

public class Request {
    private Long id;

    private Estudiante student;

    private Profesor tutor;

    private String request_date;

    private String message;

    private Long transmitter;

    private Long receiver;

    public Request(Estudiante student, Profesor tutor, String request_date, String message, Long transmitter, Long receiver) {
        this.student = student;
        this.tutor = tutor;
        this.request_date = request_date;
        this.message = message;
        this.transmitter = transmitter;
        this.receiver = receiver;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getStudent() {
        return student;
    }

    public void setStudent(Estudiante student) {
        this.student = student;
    }

    public Profesor getTutor() {
        return tutor;
    }

    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
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
