package com.dbs.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.quiz.beans.User;
import com.dbs.quiz.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepo;

	public long findUserCount() {
		return  userRepo.count();
	}

	public User createUser(User user) {
		User user1 = userRepo.save(user);
		return user1;
	}

	public User validateUser(String userName, String password) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(userName,password);
		return user;
	}

	public User validateUser(User user) {
		return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
}
