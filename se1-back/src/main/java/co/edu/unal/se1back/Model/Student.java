package co.edu.unal.se1back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name = "student")
public class Student extends User {

    @NotBlank
    private String papa;

    @NotBlank
    private String pa;

    @NotBlank
    private String pappi;

    @NotBlank
    private String carrer;

    @NotBlank
    private String progress;


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

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}