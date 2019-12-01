package co.edu.unal.se1back.repository;

import co.edu.unal.se1back.model.*;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> { }