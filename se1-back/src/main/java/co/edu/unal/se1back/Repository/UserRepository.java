package co.edu.unal.se1back.repository;

import co.edu.unal.se1back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by javergarav on 20/11/19.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}