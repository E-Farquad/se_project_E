package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

    @Query("select t from tutor t where t.id = ?1 ")
    Tutor getTutorById(Long id);

    @Query("select t.rol from tutor t where t.username = ?1 and t.password = ?2")
    String getTutorCredentials(String username, String password);

}
