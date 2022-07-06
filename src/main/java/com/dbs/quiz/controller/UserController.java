package com.dbs.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.quiz.beans.User;
import com.dbs.quiz.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public int findAllUser() {
		return (int) userService.findUserCount();
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<User> registration(@RequestBody User user) {

		User newUser = userService.createUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<User> validateLogin(@RequestBody User user) {

		User user1 = userService.validateUser(user);
		if (user1 == null) {
			return null;
		}
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}

}
