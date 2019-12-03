package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("select s from student s where s.tutor.id = ?1")
    List<Student> getStudents(Long tutorId);

}
