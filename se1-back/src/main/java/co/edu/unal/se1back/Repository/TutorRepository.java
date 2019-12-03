package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

    @Query("select t from tutor t where t.id = ?1 ")
    Tutor getTutorById(Long id);

}
