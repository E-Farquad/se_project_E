package co.edu.unal.se1back.Repository;

import co.edu.unal.se1back.Model.*;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> { }