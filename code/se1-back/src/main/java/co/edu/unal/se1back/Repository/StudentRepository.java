package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.*;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> {
    @Query("select s.tutor.id from student s where s.username = ?1")
    Long getTutorId(String username);
}