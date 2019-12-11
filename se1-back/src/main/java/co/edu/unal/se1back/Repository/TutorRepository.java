package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.*;

import javax.transaction.Transactional;

@Transactional
public interface TutorRepository extends UserBaseRepository<Tutor> { }