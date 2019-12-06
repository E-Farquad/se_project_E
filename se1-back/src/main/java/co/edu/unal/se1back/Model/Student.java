package co.edu.unal.se1back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;
import co.edu.unal.se1back.model.*;


@Entity(name = "student")
public class Student extends User {

    @NotBlank
    private String papa;

    @NotBlank
    private String pa;

    @NotBlank
    private String pappi;

    @NotBlank
    private String career;

    @NotBlank
    private String progress;

    @ManyToOne
    @JoinColumn(name="tutor_id")
    @JsonManagedReference
    private Tutor tutor;

    public Student(Long id,Long document_number, String document_type,String email, String name, String password,String rol, String username,String career,String pa,  String papa,  String pappi,  String progress, Tutor tutor) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setDocument_type(document_type);
        this.setDocument_number(document_number);
        this.setRol(rol);
        this.setEmail(email);
        this.setPapa(papa);
        this.setPa(pa);
        this.setPappi(pappi);
        this.setCareer(career);
        this.setProgress(progress);
        this.setTutor(tutor);
    }

    public String getPapa() {
        return papa;
    }

    public void setPapa(String papa) {
        this.papa = papa;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getPappi() {
        return pappi;
    }

    public void setPappi(String pappi) {
        this.pappi = pappi;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Tutor getTutor() {return tutor; }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}