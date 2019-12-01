package co.edu.unal.se1back.repository;

import co.edu.unal.se1back.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserBaseRepository<T extends User>
        extends JpaRepository<T, Long> { }
