package com.dbs.quiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.quiz.beans.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUsernameAndPassword(String userName, String password);

}
