package co.edu.unal.se1back.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import co.edu.unal.se1back.Model.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;


@Entity(name = "tutor")
public class Tutor extends User {

    @NotBlank
    private String office;

    @NotBlank
    private String office_hours;

    @NotBlank
    private String faculty;

    @NotBlank
    private String department;

    @OneToMany(mappedBy="tutor",cascade = {CascadeType.ALL})
    //@JsonBackReference
    @JsonManagedReference
    private List <Student> students;

    public Tutor(){
        super();
    }

    public Tutor(Long id,Long document_number, String document_type,String email, String name, String password,String rol, String username, String department, String faculty,String office, String office_hours, List<Student> students) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setDocument_type(document_type);
        this.setDocument_number(document_number);
        this.setRol(rol);
        this.setEmail(email);
        this.setOffice(office);
        this.setOffice_hours(office_hours);
        this.setFaculty(faculty);
        this.setDepartment(department);
        this.setStudents(students);
    }
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(String office_hours) {
        this.office_hours = office_hours;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}